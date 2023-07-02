import java.util.Scanner;
import java.io.*;

public class Farmacia2{
    
    static RandomAccessFile archivo;
    
    static void escribir(String NombreP, int CantidadP, Double Precio) throws IOException {
        archivo.seek(archivo.length()); //el puntero se posiciona al final
        archivo.writeUTF(NombreP);
        archivo.writeInt(CantidadP);
        archivo.writeDouble(Precio);
    }
    
    static boolean buscarProducto(String NombreP) throws IOException { 
        archivo.seek(0);
        while(archivo.getFilePointer() < archivo.length()) {
            if(archivo.readUTF().equals(NombreP))
                return true;
            archivo.skipBytes(6);
        }
        return false;
    }
    
    static void imprimir() throws IOException {
        archivo.seek(0);
        System.out.println("-".repeat(79));
        System.out.printf("|%-25s|%-25s|%-25s|\n", "Nombre del Farmaco", "Cantidad", "Precio");
        System.out.println("-".repeat(79));

        while(archivo.getFilePointer() < archivo.length()) {
            System.out.printf("|%-25s|%-25d|%-25f|\n",archivo.readUTF(), archivo.readInt(), archivo.readDouble());
            System.out.println("-".repeat(79));
        }
    }

    public static void main(String[] args) {

        try {
            archivo = new RandomAccessFile("Inventario.dat", "rw");
        } catch(IOException e) {
           
        }
        Scanner s= new Scanner(System.in);
        int opcion;

        do {
            System.out.print("1. Escribir en el archivo binario\n"
                    + "2. Buscar farmaco en el archivo\n"
                    + "3. Mostrar todos los datos guardados\n"
                    + "4. Salir\n"
                    + "Escriba la opcion aqui: ");
             while(!s.hasNextInt()){
                   s.next();System.out.println("Digite un valor correcto");
                    }
            opcion = s.nextInt();
            System.out.println();
            switch(opcion) {
                case 1: {
                    String NombreP;
                    int CantidadP;
                    Double Precio;
                    System.out.print("Escriba el nombre del producto: ");

                    NombreP = s.next();

                    System.out.print("Escriba la cantidad del producto: ");
                  
                   do{
                   while(!s.hasNextInt()){
                   s.next();System.out.println("Digite un valor correcto");
                    }
                    
                    
                        CantidadP = s.nextInt();

                        if(CantidadP <= 0)

                        System.out.println("Digite una cantdad valida");

                }while(CantidadP <=0);


                
                   do{
                    System.out.print("Escriba el Precio del producto: ");
                   while(!s.hasNextDouble()){
                   s.next();System.out.println("Digite un valor correcto");
                         }
                    Precio = s.nextDouble();
                    System.out.println("Digite un valor positivo");
                        }while(Precio <=0);
                    try {
                        escribir(NombreP, CantidadP, Precio);
                    } catch(IOException e) {
                        
                    }
                    System.out.println("Se han ingresado los datos correctamente!");
                    break;
                }
                case 2: {
                    String nombre;
                    System.out.print("Escriba el nombre que desea buscar: ");
                    nombre = s.next();
                    try {
                        if(buscarProducto(nombre))
                            System.out.println("Se ha encontrado el nombre en el archivo");
                        else
                            System.out.println("No se ha encontrado el nombre en el archivo");
                    } catch(IOException e) {
                        
                        System.out.println("Hubo un error al buscar el nombre en el archivo");
                    }
                    
                    break;
                }
                case 3: {
                    try {
                        imprimir();
                    } catch(IOException e) {
                        
                    }
                    break;
                }
                default: {
                    if(opcion != 4)
                        System.out.println("Opcion no valida");
                } 
            }
            System.out.println();
        } while(opcion != 4);
    }
}