package com.ejerciciocolaborativo.ejerciciocolaborativo;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EjerciciocolaborativoApplication {

	public static double promedioNotasAlumno(ArrayList <Double> array){
		double sumador = 0;
		double promAlumno = 0;

		for (int index = 0; index < array.size(); index++) {
			sumador = sumador + array.get(index);
		}

		promAlumno = sumador / array.size();

		return promAlumno;
	}
	
	public static double promedioNotasTotal(HashMap <String, ArrayList<Double>> nombreHashmap){
		System.out.println("");
		double sumadorTotal = 0;
		
		for(String i : nombreHashmap.keySet()){
			//promedioNotasAlumno(nombreHashmap.get(i));
			//System.out.println("El promedio de " + i + " es " + promedioNotasAlumno(nombreHashmap.get(i)));

			sumadorTotal = sumadorTotal + promedioNotasAlumno(nombreHashmap.get(i));
		}
		double promTotal = sumadorTotal / nombreHashmap.size();

		//System.out.println("\nEl promedio de los alumnos es: " + promTotal + "\n");

		return promTotal;
	}

	public static void aprobado(HashMap <String, ArrayList<Double>> nombreHashmap){
		double notaAzul = 4.0;

		for(String i : nombreHashmap.keySet()){
			System.out.println("Para " + i);
			for (int j = 0; j < nombreHashmap.get(i).size(); j++) {
				if (nombreHashmap.get(i).get(j) >= notaAzul) {
					System.out.println("**La nota " + nombreHashmap.get(i).get(j) + " es una nota aprobatoria.");
				} else {
					System.out.println("**La nota " + nombreHashmap.get(i).get(j) + " es una nota reprobatoria.");
				}
			}

			if (promedioNotasAlumno(nombreHashmap.get(i)) >= notaAzul) {
				System.out.println("El promedio de " + i + " es de " + promedioNotasAlumno(nombreHashmap.get(i)) + "." + "\nCorresponde a un promedio aprobatorio.");				
			} else {
				System.out.println("El promedio de " + i + " es de " + promedioNotasAlumno(nombreHashmap.get(i)) + "." + "\nNo corresponde a un promedio aprobatorio.");
			}
		}

	}

	public static void sobrePromedio(HashMap <String, ArrayList<Double>> nombreHashmap){
		System.out.println("");

		for(String i : nombreHashmap.keySet()){
			if (promedioNotasAlumno(nombreHashmap.get(i)) > promedioNotasTotal(nombreHashmap)) {
				System.out.println(i + " tiene un promedio de " + promedioNotasAlumno(nombreHashmap.get(i)) + ", está sobre el promedio total.");
			} else if (promedioNotasAlumno(nombreHashmap.get(i)) == promedioNotasTotal(nombreHashmap)){
				System.out.println(i + " tiene un promedio de " + promedioNotasAlumno(nombreHashmap.get(i)) + ", es igual al promedio total.");
			} else {
				System.out.println(i + " tiene un promedio de " + promedioNotasAlumno(nombreHashmap.get(i)) + ", está bajo el promedio total.");
			}
		}
	}


	public static void main(String[] args) {
		HashMap <String, ArrayList<Double>> hashmapNotas = new HashMap<String, ArrayList<Double>>();
		Scanner teclado = new Scanner(System.in);
		String resp = "s";

		do {
			System.out.println("\nPor favor, ingrese el nombre del alumno a continuación:");
			String nombre = teclado.next();
			System.out.println("Ingrese la cantidad de notas de " + nombre);
			int cantNotas = teclado.nextInt();
			ArrayList <Double> arrayNotas = new ArrayList<Double>();
			for (int i = 0; i < cantNotas; i++) {
				double nota = 0;
				do {
					System.out.println("Ingrese la nota " + (i+1) + " utilizando COMAS para demarcar decimales.");
					nota = teclado.nextDouble();
				} while (nota > 7 || nota < 1);
				arrayNotas.add(nota);
			}

			hashmapNotas.put(nombre, arrayNotas);

			System.out.println("Si desea añadir otro alumno, ingrese 's' sin comillas, si no, ingrese 'n'.");
			resp = teclado.next();

		} while (!resp.equalsIgnoreCase("n"));
		
		int opcion = 1; 

		while(opcion != 0){

			do{
				System.out.println("\n");
				System.out.println("Bienvenido/a");
				System.out.println("Seleccione 1 si quiere obtener el promedio de las notas por alumno.");
				System.out.println("Seleccione 2 si quiere ver si el alumno aprueba o reprueba");
				System.out.println("Seleccione 3 para ver si la nota está sobre o por debajo del promedio general");
				System.out.println("Seleccione 0 para salir del menú");
				System.out.print("A continuación, su opción: ");
				opcion = teclado.nextInt();
				
			}while(opcion < 0 || opcion > 3);

			if(opcion == 1){
				for(String i : hashmapNotas.keySet()){
					System.out.println("El promedio de " + i + " es " + promedioNotasAlumno(hashmapNotas.get(i)));
				}
				System.out.println("\nEl promedio de los alumnos es: " + promedioNotasTotal(hashmapNotas) + "\n");
			}else if (opcion == 2){
				aprobado(hashmapNotas);
			}else if(opcion == 3){
				sobrePromedio(hashmapNotas);
		};

	}

	teclado.close();
}

}
