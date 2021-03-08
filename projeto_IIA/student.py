from Sokoban import Sokoban, State
from tree_search import *
import asyncio
import getpass
import json
import os

import websockets
from mapa import Map
from consts import Tiles

# Função que traduz uma sequência de estados em inputs para o servidor
def goTo(pos1,pos2):
    if pos1[1] - pos2[1] == 0: #mesma linha
        if pos1[0] < pos2[0]: # mov para a direita
            return "d"
        else: #mov para a esquerda
            return "a"
    if pos1[0] - pos2[0] == 0: #mesma coluna
        if pos1[1] < pos2[1]: # mov para baixo
            return "s"
        else: #mov para cima
            return "w"
    return None

def firstChar(fileName):
    if fileName != "classic1.xsb":
        return int(fileName.split(".")[0])

async def solver(puzzle, solution):
    while True:
        game_properties = await puzzle.get()
        mapa = Map(game_properties["map"])
        print(mapa)
        
        keys = ""

        g = mapa.filter_tiles([Tiles.GOAL,Tiles.BOX_ON_GOAL,Tiles.MAN_ON_GOAL])

        p = Sokoban(mapa.keeper,mapa.boxes,g,mapa)
        s = State(mapa.keeper,mapa.boxes)
        problema = SearchProblem(p,s,g)
        t = SearchTree(problema)
        
        procura = await(t.search())

        for j in range(1,len(procura)):
            keys += (goTo(procura[j-1],procura[j]))

        await solution.put(keys)

        

async def agent_loop(puzzle, solution, server_address="localhost:8000", agent_name="student"):
    async with websockets.connect(f"ws://{server_address}/player") as websocket:

        # Receive information about static game properties
        await websocket.send(json.dumps({"cmd": "join", "name": agent_name}))
        
        while True:
            try:
                update = json.loads(
                    await websocket.recv()
                )  # receive game update, this must be called timely or your game will get out of sync with the server
                
                if "map" in update:
                    # we got a new level
                    game_properties = update
                    keys = ""
                    await puzzle.put(game_properties)

                if not solution.empty():
                    keys = await solution.get()
                
                key = ""
                
                if len(keys):  # we got a solution!
                    key = keys[0]
                    keys = keys[1:]

                await websocket.send(
                    json.dumps({"cmd": "key", "key": key})
                )

            except websockets.exceptions.ConnectionClosedOK:
                print("Server has cleanly disconnected us")
                return

# DO NOT CHANGE THE LINES BELLOW
# You can change the default values using the command line, example:
# $ NAME='arrumador' python3 client.py
loop = asyncio.get_event_loop()
SERVER = os.environ.get("SERVER", "localhost")
PORT = os.environ.get("PORT", "8000")
NAME = os.environ.get("NAME", getpass.getuser())

puzzle = asyncio.Queue(loop=loop)
solution = asyncio.Queue(loop=loop)

net_task = loop.create_task(agent_loop(puzzle, solution, f"{SERVER}:{PORT}", NAME))
solver_task = loop.create_task(solver(puzzle, solution))

loop.run_until_complete(asyncio.gather(net_task, solver_task))
loop.close()