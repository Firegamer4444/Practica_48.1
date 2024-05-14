import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

public class Main {
	
	/**
	 * Muestra todos los elementos del fichero xml
	 * @param listaAutos lista de autos
	 */
	public static void mostrarElemento(ArrayList<Auto> listaAutos) {
		for (Auto auto: listaAutos) {
			System.out.println("--------------------------");
			auto.imprimirDatos();
		}
	}

	/**
	 * Retorna el auto con el id pasado por parametro
	 * @param id id del auto que quieres buscar
	 * @param listaAutos lista de autos
	 * @return
	 */
	public static Auto busqueda(String id , ArrayList<Auto> listaAutos){
		for (Auto auto: listaAutos) {
			if (auto.getId().equals(id)){
				return auto;
			}
		}
		return null;
	}


	/**
	 * menu del programa
	 * @param listaAutos
	 * @throws JAXBException 
	 * @throws IOException 
	 */
	public static void menu(Autos autos , ArrayList<Auto> listaAutos , Gson gson ) {
		while (true){
			Scanner scanner = new Scanner(System.in);
			System.out.println("\n--- Opciones: ---");
			System.out.println("1. mostrar elementos");
			System.out.println("2. buscar elemento");
			System.out.println("3. escribir datos");
			String opcion = scanner.nextLine();
			if (opcion.equals("1")){
				mostrarElemento(listaAutos);
			}
			if (opcion.equals("2")){
				System.out.println("Introduzca el id: ");
				String idBusqueda = scanner.nextLine();
				Auto autoBuscado = busqueda(idBusqueda, listaAutos);
				if (autoBuscado == null){
					System.out.println("No existe el id introducido");
				}
				else{
					System.out.println("");
					autoBuscado.imprimirDatos();
				}
			}
			if (opcion.equals("3")){
				System.out.println("Introduzca el id: ");
				String id = scanner.nextLine();
				System.out.println("Introduzca la marca: ");
				String marca = scanner.nextLine();
				System.out.println("Introduzca el modelo: ");
				String modelo = scanner.nextLine();
				System.out.println("Introduzca el año: ");
				String año = scanner.nextLine();
				System.out.println("Introduzca el color: ");
				String color = scanner.nextLine();
				System.out.println("Introduzca el tipo de motor: ");
				String tipo_motor = scanner.nextLine();
				System.out.println("Introduzca la cilindrada: ");
				String cilindrada = scanner.nextLine();
				System.out.println("Introduzca la potencia: ");
				String potencia = scanner.nextLine();
				System.out.println("Introduzca el tipo de transmision: ");
				String tipo = scanner.nextLine();
				System.out.println("Introduzca las velocidades: ");
				String velocidades = scanner.nextLine();
				Transmision nuevaTransmision = new Transmision(tipo , velocidades);
				Auto nuevoAuto = new Auto(id, marca, modelo , año, color, tipo_motor, cilindrada, potencia, nuevaTransmision);
				autos.añadirAuto(nuevoAuto);
				String autosjson = gson.toJson(autos);
				try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/resources/Autos.json"));){
            	writer.write(autosjson);
        		}
				catch (IOException e) {
					System.out.print("Error al leer el archivo");
				}
			}
		}
		}
	
	public static void main(String[] args) throws IOException {
		String autosJson = "";
		String linea = "";
		try (FileReader fileReader = new FileReader("src/main/resources/Autos.json");
        BufferedReader bufferedReader = new BufferedReader(fileReader);){
        while((linea=bufferedReader.readLine()) != null) {
				autosJson = autosJson + linea;
            }
		}
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		Autos autos = gson.fromJson(autosJson , Autos.class);
		ArrayList<Auto> listaAutos = autos.getAuto();
		menu(autos, listaAutos , gson);
	}
}