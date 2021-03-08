package lib;
public class RealType extends Type {
   public RealType() {
      super("double");
   }

   public boolean isNumeric() {
      return true;
   }
}
