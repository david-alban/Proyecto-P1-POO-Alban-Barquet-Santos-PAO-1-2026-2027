package com.proyecto.modelo;

import com.proyecto.util.ManejoArchivos;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class Sistema {

    private ArrayList<Usuario> usuarios;
    private ArrayList<Partido> partidos;
    private ArrayList<Kit> kits;
    private ArrayList<Compra> compras;

    // Constructor del método Sistema

    public Sistema() {
        this.usuarios = new ArrayList<>();
        this.compras = new ArrayList<>();
        this.kits = new ArrayList<>();
        this.partidos = new ArrayList<>();
    }

    public int buscarIndiceRespectivo(ArrayList<String> lista, String codigo) {
        for (int i = 0; i < lista.size(); i++) {
            String l = lista.get(i);
            if ((l != null) && (!l.trim().isEmpty()) && (l.startsWith(codigo))) {
                return i;
            }
        }
        return -1;
    }

    // terminado método complementario para cargar usuarios
    public void cargarUsuario() {
        ArrayList<String> lineas = com.proyecto.util.ManejoArchivos.LeeFichero("recursos/usuarios.txt");
        ArrayList<String> lineasA = com.proyecto.util.ManejoArchivos.LeeFichero("recursos/aficionados.txt");
        ArrayList<String> lineasO = com.proyecto.util.ManejoArchivos.LeeFichero("recursos/organizadores.txt");

        for (int i = 0; i < lineas.size(); i++) {
            String linea = lineas.get(i);
            if ((linea != null) && (!linea.trim().isEmpty())) {
                String[] datos = linea.split("\\|");
                String code = datos[0];

                if (linea.endsWith("A") || linea.endsWith("a")) {
                    // Es aficionado
                    int index = buscarIndiceRespectivo(lineasA, code);
                    if (index != -1) {
                        this.usuarios.add(new Aficionado(linea, lineasA.get(index)));
                    } else {
                        System.out.println("error aficionado");
                    }

                } else if (linea.endsWith("O") || linea.endsWith("o")) {
                    // Es organizador
                    int index = buscarIndiceRespectivo(lineasO, code);
                    if (index != -1) {
                        this.usuarios.add(new Organizador(linea, lineasO.get(index)));
                    } else {
                        System.out.println("error organizador");
                    }

                } else {
                    System.out.print("wtf");
                }

            }
        }

    }

    // terminado cargarUsuarios

    // me estaba botando el error Unhandled exception type ParseException
    // https://www.w3schools.com/java/java_try_catch.asp
    // https://stackoverflow.com/questions/11665195/unhandled-exception-type-parseexception
    public void cargarPartidos() {
        ArrayList<String> lineas = com.proyecto.util.ManejoArchivos.LeeFichero("recursos/partidos.txt");
        for (String linea : lineas) {
            if ((linea != null) && (!linea.trim().isEmpty())) {
                try {
                    this.partidos.add(new Partido(linea));
                } catch (Exception e) {
                    System.out.println("error al procesar fecha de partido");
                }
            }
        }
    }

    // terminado cargarPartidos
    public void cargarKits() {
        ArrayList<String> lineas = com.proyecto.util.ManejoArchivos.LeeFichero("recursos/kits.txt");
        for (String linea : lineas) {
            if ((linea != null) && (!linea.trim().isEmpty())) {

                this.kits.add(new Kit(linea,partidos));
            }
        }
    }

    public void cargarCompras() {
        if (ManejoArchivos.existeArchivo("recursos/compras.txt")) {
            ArrayList<String> lineas = com.proyecto.util.ManejoArchivos.LeeFichero("recursos/compras.txt");
            for (String linea : lineas) {
                if ((linea != null) && (!linea.trim().isEmpty())) {
                    try {
                        this.compras.add(new Compra(linea));
                    } catch (Exception e) {
                        System.out.println("error al procesar fecha de compra");
                    }
                }
            }
        } else {
            ManejoArchivos.EscribirArchivo("recursos/compras.txt",
                    "CodigoCompra|Tipo|CodigoReferencial|FechaCompra|Cantidad|ValorPagado|CodigoAficionado");
        }
    }

    public static void registrarCompra(Compra compra) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String fechaFormateada = sdf.format(compra.getFechaCompra());
        String linea = compra.getCodigoCompra() + '|' + compra.getTipo() + '|' + compra.getCodigoReferencial() + '|'
                + fechaFormateada+ '|' + compra.getCantidad() + '|' + compra.getValorPagado() + '|'
                + compra.getCodigoAficionado();
        ManejoArchivos.EscribirArchivo("recursos/compras.txt", linea);
    }

    public void pruebaCargarDatos() {
        this.cargarUsuario();
        this.cargarPartidos();
        this.cargarKits();

        System.out.println("\n--- Usuarios Cargados (" + this.usuarios.size() + ") ---");
        for (Object u : this.usuarios) {
            System.out.println(u);
        }

        System.out.println("\n--- Partidos Cargados (" + this.partidos.size() + ") ---");
        for (Partido p : this.partidos) {
            System.out.println(p);
        }

        System.out.println("\n--- Kits Cargados (" + this.kits.size() + ") ---");
        for (Kit k : this.kits) {
            System.out.println(k);
        }
    }

    public void mostrarMenu() {

    }

    public void iniciarSesion() {
        Scanner sc = new Scanner(System.in);

        System.out.println("\n=== INICIAR SESIÓN ===");
        System.out.print("Usuario: ");
        String usuario = sc.nextLine();
        System.out.print("Contraseña: ");
        String contraseña = sc.nextLine();

        Usuario usuarioAutenticado = null;

        for (Usuario u : usuarios) {
            if (u.getUsuario().equals(usuario) && u.getContraseña().equals(contraseña)) {
                usuarioAutenticado = u;
                break;
            }
        }

        if (usuarioAutenticado == null) {
            System.out.println("Credenciales incorrectas. Saliendo del sistema...");
            return;
        }

        System.out.println("\nUsuario autentificado correctamente.");

        // Polimorfismo y Downcasting 
        if (usuarioAutenticado instanceof Aficionado) {
            System.out.println("Rol detectado: AFICIONADO\n");
            Aficionado aficionado = (Aficionado) usuarioAutenticado;

            System.out.println("Bievenido/a" + aficionado.getNombres() + " " + aficionado.getApellidos() + "\n");
            System.out.println("Celular registrado: " + aficionado.getCelular());
            System.out.println("¿Este número de celular es correcto? (S/N)");
            String respuesta = sc.nextLine();

            if (respuesta.equalsIgnoreCase("S")) {
                System.out.print("Identidad confirmada. ");
            } else {
                System.out.println("Verificacion fallida.");
                System.out.println("Por motivos de seguridad se cerrará la sesión.");
                System.out.println("Saliendo del sistema...\n");

            }
        } else if (usuarioAutenticado instanceof Organizador) {
            System.out.println("Rol detectado: ORGANIZADOR\n");

            Organizador organizador = (Organizador) usuarioAutenticado;

            System.out.println("Bievenido/a" + organizador.getNombres() + " " + organizador.getApellidos() + "\n");
            System.out.println("Empresa registrada: " + organizador.getEmpresa());
            System.out.println("\n¿Esta empresa es correcta? (S/N)");
            String respuesta = sc.nextLine();

            if (respuesta.equalsIgnoreCase("S")) {
                System.out.print("Identidad confirmada. \n");
            } else {
                System.out.println("Verificacion fallida.");
                System.out.println("Por motivos de seguridad se cerrará la sesión.");
                System.out.println("Saliendo del sistema...\n");
            }
        }
    }

    //Método notificar de las entradas para aficionados
    public void notificar(Aficionado aficionado, Compra compraRealizada) {
        // Se busca el codigo referencial de acuerdo al partido que se compró
        Partido partidoComprado = null;
        for (Partido p: partidos) {
            if (p.getCodigo().equals(compraRealizada.getCodigoReferencial())) {
                partidoComprado = p;
                break;
            }
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        // Mostrar mensaje
        System.out.println("\n--- ENVIANDO NOTIFICIACIÓN ---");
        System.out.println("De correoSistema");
        System.out.println("Para: " + aficionado.getCorreo());
        System.out.println("Asunto: Compra de entrada realizada");
        System.out.println("Estimado/a " + aficionado.getNombres() + " " + aficionado.getApellidos() + ",");
        System.out.println("Su compra ha sido registrada exitosamente con el código " + compraRealizada.getCodigoCompra() + " el dia " + sdf.format(compraRealizada.getFechaCompra()) + ".");
        
        if (partidoComprado != null) {
            System.out.println("Partido: " + partidoComprado.getLocal() + " vs " + partidoComprado.getVisitante());
            System.out.println("Código del partido :" +  partidoComprado.getCodigo());
        } 

        System.out.println("Cantidad: " + compraRealizada.getCantidad());
        System.out.println("Valor Pagado: $" + compraRealizada.getValorPagado());
        System.out.println("Gracias por adquirir sus entradas para el Mundial.\n");
    }

    // Método notificar de los kits para aficionados
    public void notificar(Aficionado aficionado, Compra compraRealizada, Kit kitAdquirido) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        //Mostrar Mensaje
        System.out.println("\n--- ENVIANDO NOTIFICIACIÓN ---");
        System.out.println("De correoSistema");
        System.out.println("Para: " + aficionado.getCorreo());
        System.out.println("Asunto: Compra de kit realizada");
        System.out.println("Estimado/a " + aficionado.getNombres() + " " + aficionado.getApellidos() + ",");
        System.out.println("Su compra ha sido registrada exitosamente con el código " + compraRealizada.getCodigoCompra() + " el dia " + sdf.format(compraRealizada.getFechaCompra()) + ".");
        
        
        System.out.println("Kit adquirido: " + kitAdquirido.getNombre());
        System.out.println("Código del kit: " + kitAdquirido.getCodigo());
        System.out.println("Cantidad de kits: " + compraRealizada.getCantidad());
        System.out.println("Valor total pagado: $" + compraRealizada.getValorPagado());
    }

    // Método notificar de los reportes para organizadores
    public void notificar(Organizador organizador, String datosReporte) {
        System.out.println("\n--- ENVIANDO NOTIFICIACIÓN ---");
        System.out.println("De correoSistema");
        System.out.println("Para: " + organizador.getCorreo());
        System.out.println("Asunto: Reporte de compras registradas");
        System.out.println("Estimado/a " + organizador.getNombres() + " " + organizador.getApellidos() + ",");
        System.out.println("Se ha generado el reporte de compras del sistema.");

        System.out.println(datosReporte);
    }

    public ArrayList<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(ArrayList<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public ArrayList<Partido> getPartidos() {
        return partidos;
    }

    public void setPartidos(ArrayList<Partido> partidos) {
        this.partidos = partidos;
    }

    public ArrayList<Kit> getKits() {
        return kits;
    }

    public void setKits(ArrayList<Kit> kits) {
        this.kits = kits;
    }

    public ArrayList<Compra> getCompras() {
        return compras;
    }

    public void setCompras(ArrayList<Compra> compras) {
        this.compras = compras;
    }

    // Método consultar partidos
    public void consultarPartidos() {
        if (partidos.isEmpty()) {
            System.out.println("No hay partidos registrados en el sistema.");
            return;
        }

        System.out.println("Partidos encontrados: \n");

        int i = 1;
        for (Partido partido : partidos) {
            if (partido != null) {
                System.out.print(i + ". ");
                System.out.println(partido);
                System.out.println("\n-----------------------------------------\n\n");
                i++;
            }
        }
    }

    public void consultarKits() {
        if (partidos.isEmpty()) {
            System.out.println("No hay kits cargados en el sistema.");
            return;
        }

        System.out.println("Kits encontrados: \n");

        int i = 1;
        for (Kit kit : kits) {
            if (kit != null) {
                System.out.print(i + ". ");
                System.out.println(kit);
                System.out.println("\n-----------------------------------------\n\n");
                i++;
            }
        }
    }

    
        




}
