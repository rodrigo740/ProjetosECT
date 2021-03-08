from random import randint
from tree_search import *
from mapa import Map
from consts import Tiles

# Classe que representa o dominio de pesquisa do Sokoban:
# Posição do keeper - self.keeper
# Posição das caixas - self.boxes
# Mapa de jogo - self.mapa
# Coordenadas em volta de um estado - self.dict
# Conjunto das deadPositions - self.deadPosition
# Lista com as combinações de caixas com goals - self.mbg

class Sokoban(SearchDomain):
    
    def __init__(self,keeper,boxes,goals,mapa):
        self.keeper = keeper
        self.boxes = boxes
        self.goals = set(goals)
        self.mapa = mapa
        self.walls = set(mapa.filter_tiles([Tiles.WALL]))
        self.dict = None
        self.deadPosition = self.deadPositions(mapa)
        self.mbg = self.heuristicMatch()
        self.notDeadlock = set()

    # Função que remove as deadPositions da área de jogo.
    # DeadPositions sao posições ou conjuntos de posições em que se alguma caixa lá entrar não
    # consegue chegar a nenhum dos goals do mapa.
    # De momento a função só elimina as deadPositions que estão junto das bordas do mapa
    def deadPositions(self,mapa):

        dpVertical1 = set()
        for i in range(1,mapa.size[1]-1):
            pos = (1,i)
            if pos in self.goals:
                dpVertical1 = set()
                break
            dpVertical1.add(pos)

        dpVertical2 = set()
        for i in range(1,mapa.size[1]-1):
            pos = (mapa.size[0]-2,i)
            if pos in self.goals: 
                dpVertical2 = set()
                break
            dpVertical2.add(pos)
        
        dpHorizontal1 = set()
        for i in range(1,mapa.size[0]-1):
            pos = (i,1)
            if pos in self.goals:
                dpHorizontal1 = set()
                break
            dpHorizontal1.add(pos)

        dpHorizontal2 = set()
        for i in range(1,mapa.size[0]-1):
            pos = (i,mapa.size[1]-2)
            if pos in self.goals: 
                dpHorizontal2 = set()
                break
            dpHorizontal2.add(pos)

        dps = dpVertical1 | dpVertical2 | dpHorizontal1 | dpHorizontal2
        walls = mapa.filter_tiles([Tiles.WALL])
        dps = dps - set(walls)
        return dps

    # Funcao que retorna todas as ações possíveis num estado
    def actions(self,state):
        pos = state.keeper
        self.dict = {'a' : (pos[0]-1,pos[1]),
                     'd' : (pos[0]+1,pos[1]),
                     's' : (pos[0],pos[1]-1),
                     'w' : (pos[0],pos[1]+1)}
        actions = []
        for p in self.dict:
            if self.dict[p] not in self.walls:
                actions.append(p)
        return actions

    # Todos os moves no jogo do sokoban tem custo 1
    def cost(self, state, action):
        return 1

    # A heuristica de um estado é a soma das distâncias entre as caixas e os goals
    def heuristic(self,state):
        h = 0
        i = 0
        for b in state.boxes:
            h += abs(b[0]-self.mbg[i][0])+abs(b[1]-self.mbg[i][1])
            i+=1
        return h

    # Função que combina cada caixa com o goal mais proximo para depois ser calculada a heuristica
    def heuristicMatch(self):
        l = self.matchBoxGoal()
        listHeur = []
        for i in l:
            h = abs(i[0][0]-i[1][0])+abs(i[0][1]-i[1][1])
            t = (i,h)
            listHeur.append(t) 
        listHeur.sort(key=lambda x: x[1])
        match = []
        for i in listHeur:
            f = True
            if match:
                for n in range(len(match)):
                    if match[n][0][0] == i[0][0] or match[n][0][1] == i[0][1]:
                        f = False
            if f: match.append(i)
        ordenado = [match[m][0][1] for b in self.boxes for m in range(len(match)) if match[m][0][0] == b]
        return ordenado

    def matchBoxGoal(self):
        l = []
        for i in self.boxes:
            for j in self.goals:
                tup = (i,j)
                l.append(tup)
        return l

    # Função que executa uma ação num determinado estado e retorna o resultado, caso não seja uma ação
    # válida, retorna None
    def result(self, state, action):
        #start = time.clock()
        if self.dict[action] in state.boxes:
            pos = state.checkMove(state.keeper,self.dict[action])
            if pos not in self.walls and pos not in state.boxes:
                if pos not in self.notDeadlock:
                    if pos not in self.deadPosition:
                        if state.deadlockCheck(pos,self.goals,self.mapa) == False:
                            self.notDeadlock.add(pos)
                            b = set()
                            for box in list(state.boxes):
                                if box == self.dict[action]:
                                    b.add(pos)
                                else:
                                    b.add(box)

                            temp = State(self.dict[action] ,b)
                            return temp
                        else:
                            self.deadPosition.add(pos)
                else:
                    b = set()
                    for box in list(state.boxes):
                        if box == self.dict[action]:
                            b.add(pos)
                        else:
                            b.add(box)

                    temp = State(self.dict[action] ,b)
                    return temp

        else:
            temp = State(self.dict[action] ,state.boxes)
            return temp

    # Verificar se o estado atual é solução do problema
    def satisfies(self, state, goal):
        boxes_on_goal = 0
        for b in state.boxes:
            for g in goal:
                if g == b:
                    boxes_on_goal+=1
                    continue
        
        return boxes_on_goal == len(goal)

# /*************************************************************************************************************/

# Classe que define um estado no Sokoban
# Posição do keeper - self.keeper
# Posição das caixas - self.boxes
# Heuristica do estado - self.heuristic

class State():
    def __init__(self,keeper,boxes):
        self.keeper = keeper
        self.boxes = boxes

    # Função que calcula a posição para que a caixa vai ser empurrada
    def checkMove(self,pos1,pos2):
        posToCheck = ()
        if pos1[0] - pos2[0] == 0: #mesma coluna
            if pos1[1] > pos2[1]: # mov para cima
                posToCheck = (pos2[0],pos2[1]-1)
            else: #mov para baixo
                posToCheck = (pos2[0],pos2[1]+1)
        elif pos1[1] - pos2[1] == 0: #mesma linha
            if pos1[0] < pos2[0]: # mov para a direita
                posToCheck = (pos2[0]+1,pos2[1])
            else: #mov para a esquerda
                posToCheck = (pos2[0]-1,pos2[1])
        return posToCheck

    
            
    # Função que verifica se a posição para onde a caixa vai ser empurrada é um deadlock
    def deadlockCheck(self,pos,goals,mapa):
        if pos in goals: return False

        c = pos[0],pos[1]+1
        b = pos[0],pos[1]-1
        d = pos[0]+1,pos[1]
        e = pos[0]-1,pos[1]

        walls = mapa.filter_tiles([Tiles.WALL])

        #entre paredes
        if (c in walls) and (d in walls): return True
        if (c in walls) and (e in walls): return True
        if (b in walls) and (d in walls): return True
        if (b in walls) and (e in walls): return True
        return False


    def __str__(self):
        return "state(" + str(self.keeper) + "," + str(self.boxes) + ")"

    def __eq__(self, other):
        if isinstance(other, State):
            if self.keeper == other.keeper and self.boxes == other.boxes:
                return True
            else:
                return False
    
    def __hash__(self):
        return hash((tuple(self.boxes),self.keeper))
