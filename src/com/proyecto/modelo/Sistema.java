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
        this.partidos = new ArrayList<>();
        this.kits = new ArrayList<>();

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

                this.kits.add(new Kit(linea, partidos));
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

    public void registrarCompra(Compra compra) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String fechaFormateada = sdf.format(compra.getFechaCompra());
        String linea = compra.getCodigoCompra() + "|" + compra.getTipo() + '|' + compra.getCodigoReferencial() + '|'
                + fechaFormateada + '|' + compra.getCantidad() + '|' + compra.getValorPagado() + '|'
                + compra.getCodigoAficionado();
        ManejoArchivos.EscribirArchivo("recursos/compras.txt", linea);
        compras.add(compra);
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
        Scanner sc = new Scanner(System.in);
        this.cargarUsuario();
        this.cargarCompras();
        this.cargarPartidos();
        this.cargarKits();
        while (true) {
            Usuario userLogeado = this.iniciarSesion(sc);

            if (userLogeado == null) {
                return;
            }

            boolean mientras = true;

            while (mientras) {
                if (userLogeado instanceof Aficionado) {
                    System.out.println("\n --- Menu Aficionado ---");
                    System.out.println("1. Consultar partidos\r\n" + //
                            "2. Comprar entrada\r\n" + //
                            "3. Comprar kit de entradas\r\n" + //
                            "4. Consultar entradas\r\n" + //
                            "5. Salir\r\n" +
                            "Seleccione una opcion: ");

                    String eleccion = sc.nextLine();
                    switch (eleccion) {
                        case "1": // consultar partidos
                            userLogeado.consultarPartidos(partidos);
                            break;
                        case "2": // comprar entrada

                            System.out.println("\n---Comprar Entrada---");

                            userLogeado.consultarPartidos(partidos);

                            System.out.println("Ingrese el codigo del partido que desea comprar: ");

                            // 1 Ingresar código

                            String codePartido = sc.nextLine();

                            Partido pSeleccionado = null;
                            for (Partido p : partidos) {
                                if (p.getCodigo().equals(codePartido)) {
                                    pSeleccionado = p;
                                    break;
                                }

                            }

                            if (pSeleccionado == null) {
                                System.out.println("Código de partido no encontrado. Cancelando compra...");
                                break;
                            }

                            // 2 Seleccionar zona
                            System.out.println("Seleccione la zona (GENERAL / PREFERENCIAL / VIP");
                            String zonaE = sc.nextLine();
                            Zona zonaSelec = null;
                            if (zonaE.equalsIgnoreCase("VIP")) {
                                zonaSelec = Zona.VIP;
                            }
                            if (zonaE.equalsIgnoreCase("GENERAL")) {
                                zonaSelec = Zona.General;
                            }
                            if (zonaE.equalsIgnoreCase("PREFERENCIAL")) {
                                zonaSelec = Zona.Preferencial;
                            }

                            // 3 Validar entradas

                            boolean validarStock = false;

                            if ((zonaSelec == Zona.VIP) && (pSeleccionado.getStockVIP() > 0)) {
                                validarStock = true;
                            } else if ((zonaSelec == Zona.Preferencial) && (pSeleccionado.getStockPreferencial() > 0)) {
                                validarStock = true;
                            } else if ((zonaSelec == Zona.General) && (pSeleccionado.getStockGeneral() > 0)) {
                                validarStock = true;
                            }

                            if (validarStock == false) {
                                System.out.println("Lo sentimos, no hay stock disponible, se cancelo su compra.");
                                break;
                            }

                            // 4 Ingresar cantidad de entradas

                            System.out.println("Ingrese la cantidad de entradas: ");
                            int cantidadEntradas = sc.nextInt();
                            sc.nextLine();

                            // 5 mostrar total a pagar
                            // downcasting
                            Compra nuevaCompra = ((Aficionado) userLogeado).comprar(pSeleccionado, zonaSelec,
                                    cantidadEntradas);
                            System.out.println("Total a pagar: " + nuevaCompra.getValorPagado());

                            // 6 ingresar numero de tarjeta
                            System.out.println("Ingrese el numero de su tarjeta: ");
                            sc.nextLine();

                            // 7 simular pago exitoso
                            System.out.println("Procesando pago");
                            System.out.println("Pago exitoso!");

                            this.registrarCompra(nuevaCompra);
                            this.notificar(((Aficionado) userLogeado), nuevaCompra); // downcasting

                            if ((zonaSelec == Zona.VIP)) {
                                pSeleccionado.setStockVIP(pSeleccionado.getStockVIP() - cantidadEntradas);
                            } else if ((zonaSelec == Zona.Preferencial)) {
                                pSeleccionado
                                        .setStockPreferencial(pSeleccionado.getStockPreferencial() - cantidadEntradas);
                            } else if ((zonaSelec == Zona.General)) {
                                pSeleccionado.setStockGeneral(pSeleccionado.getStockGeneral() - cantidadEntradas);
                            }

                            break;
                        case "3": // comprar kit de entradas
                            System.out.println("\n--- Comprar KIT de Entradas---");
                            userLogeado.consultarKits(kits);

                            System.out.println("Ingrese el codigo del kit que desea comprar: ");
                            String codeKit = sc.nextLine();

                            Kit kitSeleccionado = null;

                            for (Kit k : kits) {
                                if (k.getCodigo().equals(codeKit)) {
                                    kitSeleccionado = k;
                                    break;
                                }
                            }

                            if (kitSeleccionado == null) {
                                System.out.println("Lo siento el codigo ingresado es incorrecto");
                                break;
                            }

                            System.out.println("Ingrese la cantidad de kits que desea: ");
                            int cantidadKits = sc.nextInt();
                            sc.nextLine();

                            Compra nuevaCompraKit = ((Aficionado) userLogeado).comprar(kitSeleccionado, cantidadKits);
                            System.out.println("Total a pagar: " + nuevaCompraKit.getValorPagado());

                            System.out.println("Ingrese el numero de su tarjeta");
                            sc.nextLine();

                            System.out.println("Procesando pago...");
                            System.out.println("pago exitoso");

                            this.registrarCompra(nuevaCompraKit);
                            this.notificar(((Aficionado) userLogeado), nuevaCompraKit, kitSeleccionado);

                            break;
                        case "4": // consultar entradas compradas
                            ((Aficionado) userLogeado).consultarEntradas(compras);
                            break;
                        case "5":
                            System.out.println("Saliendo del menu");
                            mientras = false;
                            break;

                        default:
                            break;
                    }
                }
                if (userLogeado instanceof Organizador) {
                    System.out.println("\n --- Menu Organizador ---");
                    System.out.println("1. Consultar partidos\r\n" + //
                            "2. Generar Reportes\r\n" + //
                            "3. Salir\r\n" +
                            "Seleccione una opcion: ");
                    String eleccion = sc.nextLine();
                    switch (eleccion) {
                        case "1":
                            System.out.println("\n---Consultando partidos del sistema---");
                            userLogeado.consultarEntradas(compras);
                            break;
                        case "2":
                            System.out.println("\n---Generando Reporte de Ventas---");
                            // Downcasting para llamar al método requerido del Organizador
                            Organizador org = (Organizador) userLogeado;
                            org.generarReporte(compras);
                            this.notificar(org,
                                    "El reporte detallado se ha mostrado exitosamente en la consola del sistema ");
                            break;
                        case "3":
                            System.out.println("Saliendo del menú del Organizador...");
                            mientras = false;
                            break;

                        default:
                            System.out.println("Opción inválida. Por favor, seleccione una opción válida.");
                            break;
                    }
                }
            }
        }
    }

    public Usuario iniciarSesion(Scanner sc) {

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
            return null;
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
                return usuarioAutenticado;
            } else {
                System.out.println("Verificacion fallida.");
                System.out.println("Por motivos de seguridad se cerrará la sesión.");
                System.out.println("Saliendo del sistema...\n");
                return null;

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
                return usuarioAutenticado;
            } else {
                System.out.println("Verificacion fallida.");
                System.out.println("Por motivos de seguridad se cerrará la sesión.");
                System.out.println("Saliendo del sistema...\n");
                return null;
            }
        }
        return null;
    }

    // Método notificar de las entradas para aficionados
    public void notificar(Aficionado aficionado, Compra compraRealizada) {
        // Se busca el codigo referencial de acuerdo al partido que se compró
        Partido partidoComprado = null;
        for (Partido p : partidos) {
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
        System.out.println("Su compra ha sido registrada exitosamente con el código "
                + compraRealizada.getCodigoCompra() + " el dia " + sdf.format(compraRealizada.getFechaCompra()) + ".");

        if (partidoComprado != null) {
            System.out.println("Partido: " + partidoComprado.getLocal() + " vs " + partidoComprado.getVisitante());
            System.out.println("Código del partido :" + partidoComprado.getCodigo());
        }

        System.out.println("Cantidad: " + compraRealizada.getCantidad());
        System.out.println("Valor Pagado: $" + compraRealizada.getValorPagado());
        System.out.println("Gracias por adquirir sus entradas para el Mundial.\n");
    }

    // Método notificar de los kits para aficionados
    public void notificar(Aficionado aficionado, Compra compraRealizada, Kit kitAdquirido) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        // Mostrar Mensaje
        System.out.println("\n--- ENVIANDO NOTIFICIACIÓN ---");
        System.out.println("De correoSistema");
        System.out.println("Para: " + aficionado.getCorreo());
        System.out.println("Asunto: Compra de kit realizada");
        System.out.println("Estimado/a " + aficionado.getNombres() + " " + aficionado.getApellidos() + ",");
        System.out.println("Su compra ha sido registrada exitosamente con el código "
                + compraRealizada.getCodigoCompra() + " el dia " + sdf.format(compraRealizada.getFechaCompra()) + ".");

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

}
