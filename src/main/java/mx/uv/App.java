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

        port(80);

        options("/*", (request, response) -> {
            String accessControlRequestHeaders = request.headers("Access-Control-Request-Headers");
            if (accessControlRequestHeaders != null) {
                response.header("Access-Control-Allow-Headers", accessControlRequestHeaders);
            }
            String accessControlRequestMethod = request.headers("Access-Control-Request-Method");
            if (accessControlRequestMethod != null) {
                response.header("Access-Control-Allow-Methods", accessControlRequestMethod);
            }
            return "OK";
        });
        before((req, res) -> res.header("Access-Control-Allow-Origin", "*"));

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

        post("/register" ,(req,res)-> {
            String datosFormulario = req.body();
            Usuario u = gson.fromJson(datosFormulario, Usuario.class);
            String x= DAO.crearUsuario(u);
            uAux.setNombre(u.getNombre());
            uAux.setPassword(u.getPassword());
            return x;
        });

        get("/main",(req,res)-> {
            return  gson.toJson(DAOEvento.dameEventos(uAux.getNombre()));
        });

        post("/mainBM",(req,res)-> {
            String datosEvento = req.body();
            Evento ev = gson.fromJson(datosEvento, Evento.class);
            System.out.println(ev.getTitulo());
            return gson.toJson(DAOEvento.dameEvento(uAux.getNombre(), ev.getTitulo()));
        });

        post("/main" ,(req,res)-> {
            String datosEvento = req.body();
            Evento ev = gson.fromJson(datosEvento, Evento.class);
            ev.setUsuario(uAux.getNombre());
            String x= DAOEvento.crearEvento(ev);
            return x;
        });

        post("/mainD" ,(req,res)-> {
            String datosEvento = req.body();
            Evento ev = gson.fromJson(datosEvento, Evento.class);
            System.out.println(ev.getTitulo());
            String x= DAOEvento.borraEvento(ev.getTitulo());
            return x;
        });
        
        post("/mainM" ,(req,res)-> {
            String datosEvento = req.body();
            Evento ev = gson.fromJson(datosEvento, Evento.class);
            System.out.println(ev.getTitulo());
            String x= DAOEvento.modificaEvento(ev);
            return x;
        });
        
    }
}
