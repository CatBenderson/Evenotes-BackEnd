package mx.uv;

import static spark.Spark.*;

import com.google.gson.Gson;

/**
 * Hello world!
 *
 */
public class App {
    public static Gson gson= new Gson();
    public static void main( String[] args ){
        Usuario uAux = new Usuario();

        port(getHerokuAssignedPort());

        // Aqui va el CORS
        options("/*", (request, response) -> {
            String accessControlRequestHeaders = request.headers("Access-Control-Request-Headers");
            System.out.println(accessControlRequestHeaders);
            if (accessControlRequestHeaders != null) {
                response.header("Access-Control-Allow-Headers", accessControlRequestHeaders);
            }
            String accessControlRequestMethod = request.headers("Access-Control-Request-Method");
            System.out.println(accessControlRequestMethod);
            if (accessControlRequestMethod != null) {
                response.header("Access-Control-Allow-Methods", accessControlRequestMethod);
            }
            return "OK";
        });
        before((req, res)-> res.header("Access-Control-Allow-Origin", "*"));

        post("/login", (req, res) -> {
            String datosFormulario = req.body();
            Usuario u=gson.fromJson(datosFormulario, Usuario.class);
            if (DAO.compruebaUsuario(u)==true ){
                uAux.setNombre(u.getNombre());
                uAux.setPassword(u.getPassword());
                return true;
            }else{
                return false;
            }
        });
        
        before((req, res)-> res.header("Access-Control-Allow-Origin", "*"));
        post("/register" ,(req,res)-> {
            System.out.println("Entra a registro");
            String datosFormulario = req.body();
            Usuario u = gson.fromJson(datosFormulario, Usuario.class);
            String x= DAO.crearUsuario(u);
            uAux.setNombre(u.getNombre());
            uAux.setPassword(u.getPassword());
            return x;
        });

        before((req, res)-> res.header("Access-Control-Allow-Origin", "*"));
        get("/main",(req,res)-> {
            return  gson.toJson(DAOEvento.dameEventos(uAux.getNombre()));
        });

        before((req, res)-> res.header("Access-Control-Allow-Origin", "*"));
        post("/mainBM",(req,res)-> {
            String datosEvento = req.body();
            Evento ev = gson.fromJson(datosEvento, Evento.class);
            System.out.println(ev.getTitulo());
            return gson.toJson(DAOEvento.dameEvento(uAux.getNombre(), ev.getTitulo()));
        });

        before((req, res)-> res.header("Access-Control-Allow-Origin", "*"));
        post("/main" ,(req,res)-> {
            String datosEvento = req.body();
            Evento ev = gson.fromJson(datosEvento, Evento.class);
            ev.setUsuario(uAux.getNombre());
            String x= DAOEvento.crearEvento(ev);
            return x;
        });

        before((req, res)-> res.header("Access-Control-Allow-Origin", "*"));
        post("/mainD" ,(req,res)-> {
            String datosEvento = req.body();
            Evento ev = gson.fromJson(datosEvento, Evento.class);
            System.out.println(ev.getTitulo());
            String x= DAOEvento.borraEvento(ev.getTitulo());
            return x;
        });

        before((req, res)-> res.header("Access-Control-Allow-Origin", "*"));
        post("/mainM" ,(req,res)-> {
            String datosEvento = req.body();
            Evento ev = gson.fromJson(datosEvento, Evento.class);
            System.out.println(ev.getTitulo());
            String x= DAOEvento.modificaEvento(ev);
            return x;
        });
    }
    
    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567; // return default port if heroku-port isn't set (i.e. on localhost)
    }
}
