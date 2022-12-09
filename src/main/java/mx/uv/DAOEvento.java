package mx.uv;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class DAOEvento {
    public static Conexion c = new Conexion();

    public static String crearEvento(Evento ev){
        PreparedStatement stm =null;
        Connection cc=null;
        String message = "";

        cc = c.getConnection();

        try {
            String sql = "insert into evento(titulo,fecha,horaInicio,horaFin,lugar,descripcion,usuario) values (?,?,?,?,?,?,?);";
            stm=cc.prepareStatement(sql);
            stm.setString(1, ev.getTitulo());
            stm.setString(2, ev.getFecha());
            stm.setString(3, ev.getHoraInicio());
            stm.setString(4, ev.getHoraFin());
            stm.setString(5, ev.getLugar());
            stm.setString(6, ev.getDescripcion());
            stm.setString(7, ev.getUsuario());

            if (stm.executeUpdate()>0){
                message = "Evento creado";
            }else{
                message="El evento no se ha podido crear";
            }
        } catch (Exception e) {
            System.out.println(e);
        }finally {
            if (stm != null) {
                try {
                    stm.close();
                } catch (SQLException sqlEx) {
                    sqlEx.printStackTrace();
                }
                stm = null;
            }
            try {
                cc.close();
                System.out.println("Closed  connection!");
            } catch (SQLException sqlEx) {
                sqlEx.printStackTrace();
            }
        }
        
        return message;
    }

    public static boolean compruebaEvento(Evento ev) {
            Statement stm=null;
            ResultSet rs = null;
            Connection cc =null;
    
            cc = c.getConnection();
            try {
                String sql = "select count(id) as i FROM usuarios where nombre= '"+ev.getTitulo()+";";
                stm = (Statement) cc.createStatement();
                rs = ((java.sql.Statement) stm).executeQuery(sql);
                
                while (rs.next()) {
                    if(rs.getString("i").equals("1")){
                        return true;
                    }else{
                        return false;
                    }
                }
            } catch (Exception e) {
                System.out.println(e +"1");
            } finally {
                if (rs != null) {
                    try {
                        rs.close();
                    } catch (SQLException sqlEx) {
                        sqlEx.printStackTrace();
                    }
                    rs = null;
                }
    
                if (stm != null) {
                    try {
                        stm.close();
                    } catch (SQLException sqlEx) {
                        sqlEx.printStackTrace();
                    }
                    stm = null;
                }
                try {
                    cc.close();
                    System.out.println("Closed  connection!");
                } catch (SQLException sqlEx) {
                    sqlEx.printStackTrace();
                }
            }    
            return false;   
        }

        public static String borraEvento(String ev){
            PreparedStatement stm =null;
            Connection cc=null;
            String message = "";
    
            cc = c.getConnection();
    
            try {
                String sql = "DELETE FROM evenotesdb.evento WHERE (titulo = ? );";
                stm=cc.prepareStatement(sql);
                stm.setString(1, ev);
    
                if (stm.executeUpdate()>0){
                    message = "El evento se eliminó";
                }else{
                    message="El evento no se eliminó";
                }
            } catch (Exception e) {
                System.out.println(e);
            }finally {
                if (stm != null) {
                    try {
                        stm.close();
                    } catch (SQLException sqlEx) {
                        sqlEx.printStackTrace();
                    }
                    stm = null;
                }
                try {
                    cc.close();
                    System.out.println("Closed  connection!");
                } catch (SQLException sqlEx) {
                    sqlEx.printStackTrace();
                }
            }
            
            return message;
        }
    
        public static String modificaEvento(Evento ev){
            PreparedStatement stm =null;
            Connection cc=null;
            String message = "";
    
            cc = c.getConnection();
    
            try {
                String sql = "UPDATE evenotesdb.evento SET titulo = ?, fecha = ?, horaInicio = ?, horaFin = ?, lugar = ?, descripcion = ? WHERE (titulo = ?);";
                stm=cc.prepareStatement(sql);
                stm.setString(1, ev.getTitulo());
                stm.setString(2, ev.getFecha());
                stm.setString(3, ev.getHoraInicio());
                stm.setString(4, ev.getHoraFin());
                stm.setString(5, ev.getLugar());
                stm.setString(6, ev.getDescripcion());
                stm.setString(7, ev.getTitulo());
    
                if (stm.executeUpdate()>0){
                    message = "El evento se modificó";
                }else{
                    message="El evento no se modificó";
                }
            } catch (Exception e) {
                System.out.println(e);
            }finally {
                if (stm != null) {
                    try {
                        stm.close();
                    } catch (SQLException sqlEx) {
                        sqlEx.printStackTrace();
                    }
                    stm = null;
                }
                try {
                    cc.close();
                    System.out.println("Closed  connection!");
                } catch (SQLException sqlEx) {
                    sqlEx.printStackTrace();
                }
            }
            
            return message;
        }

        public static List<Evento> dameEventos(String usuario){

            Statement stm=null;
            ResultSet rs = null;
            List<Evento> resultado = new ArrayList<>();
            Connection cc =null;
    
            cc = c.getConnection();
            try {
                String sql = "select * from evento where usuario='"+usuario+"' ORDER BY fecha ASC , horaInicio ASC";
                stm = (Statement) cc.createStatement();
                rs = ((java.sql.Statement) stm).executeQuery(sql);
                while (rs.next()) {
                    Evento ev = new Evento(
                     rs.getString("id"),
                     rs.getString("titulo"),
                     rs.getString("fecha"),
                     rs.getString("horaInicio"),
                     rs.getString("horaFin"),
                     rs.getString("lugar"),
                     rs.getString("descripcion"),
                     rs.getString("usuario"));
                     resultado.add(ev);
                }
            } catch (Exception e) {
                System.out.println(e);
            } finally {
                if (rs != null) {
                    try {
                        rs.close();
                    } catch (SQLException sqlEx) {
                        sqlEx.printStackTrace();
                    }
                    rs = null;
                }
    
                if (stm != null) {
                    try {
                        stm.close();
                    } catch (SQLException sqlEx) {
                        sqlEx.printStackTrace();
                    }
                    stm = null;
                }
                try {
                    cc.close();
                    System.out.println("Closed  connection!");
                } catch (SQLException sqlEx) {
                    sqlEx.printStackTrace();
                }
            }
            
            return resultado;
        }

        public static Evento dameEvento(String usuario,String titulo){

            Statement stm=null;
            ResultSet rs = null;
            Evento resultado = new Evento();
            Connection cc =null;
    
            cc = c.getConnection();
            try {
                String sql = "select * from evento where usuario='"+usuario+"' and titulo ='"+titulo+"'";
                stm = (Statement) cc.createStatement();
                rs = ((java.sql.Statement) stm).executeQuery(sql);
                while (rs.next()) {
                     resultado.setId(rs.getString("id"));
                     resultado.setTitulo(rs.getString("titulo"));
                     resultado.setFecha(rs.getString("fecha"));
                     resultado.setHoraInicio(rs.getString("horaInicio"));
                     resultado.setHoraFin(rs.getString("horaFin"));
                     resultado.setLugar(rs.getString("lugar"));
                     resultado.setDescripcion(rs.getString("descripcion"));
                     resultado.setUsuario(rs.getString("usuario"));
                }
            } catch (Exception e) {
                System.out.println(e);
            } finally {
                if (rs != null) {
                    try {
                        rs.close();
                    } catch (SQLException sqlEx) {
                        sqlEx.printStackTrace();
                    }
                    rs = null;
                }
    
                if (stm != null) {
                    try {
                        stm.close();
                    } catch (SQLException sqlEx) {
                        sqlEx.printStackTrace();
                    }
                    stm = null;
                }
                try {
                    cc.close();
                    System.out.println("Closed  connection!");
                } catch (SQLException sqlEx) {
                    sqlEx.printStackTrace();
                }
            }
            return resultado;
        }
}