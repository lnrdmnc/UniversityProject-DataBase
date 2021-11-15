import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Date;
import java.util.*;

    public class Query {
        
        public void connessione() {           
            try {
                 Class.forName("com.mysql.cj.jdbc.Driver");
                 String url = "jdbc:mysql://localhost:3306/progetto"
                            + "?useUnicode=true&useJDBCCompliantTimezoneShift=true"
                            + "&useLegacyDatetimeCode=false&serverTimezone=UTC";
                 String username = "root";
                 String pwd = "admin";
                 con = DriverManager.getConnection(url, username, pwd);
               }catch(Exception e) {
                                    e.printStackTrace();
                                   }
        }
        public void query1(String nomeRistorante, String numeroGiornaliero, Date date2, String tipo, String descrizione, String stato, String idCliente, String idRistorante){
            try {              
                 Statement pquery = con.createStatement();
                 int result = pquery.executeUpdate( "INSERT INTO ordine(numero_giornaliero,data1,tipo,descrizione,stato,id_cliente,id_ristorante) "
                                                  + "select '"+numeroGiornaliero+"',"+"'"+date2+"'"+",'"+tipo+"','"+descrizione+"','"+stato+"','"+idCliente+"','"+idRistorante+"' "
                                                  + "from ristorante "
                                                  + "WHERE nome = '" + nomeRistorante+"'");
                 
                 int update= pquery.executeUpdate( "UPDATE ristorante "
                		                         + "set ristorante.coda_ordini = (SELECT distinct count(*) "
                		                                                         + "from (select * from ristorante) as ristorante_temporaneo join ordine on ordine.id_ristorante=ristorante_temporaneo.id_ristorante "
                		                                                         + "WHERE ordine.stato= 'ordinato' and ristorante_temporaneo.nome='"+nomeRistorante+"') "
                		                         + " WHERE ristorante.nome='" +nomeRistorante +"';");
                 
                if(result==1) System.out.println("Ordine effettuato correttamente");
                else System.out.println("Errore nell'effettuare l'ordine");
                
               }catch (Exception e) {
                                     e.printStackTrace();
                                    }
        }
        
        public void query2(String nome,String cognome,boolean choise,String data1,String orarioPresunto){
     	   try {
     		    Statement pquery = con.createStatement();
     		    String cf_rider=null;
     		    String cf_dipendente=null;
     		    String idCliente=null;
     		    String nominativo_ricevente=null;
     		    ResultSet result;
     		    String dipendenteNome=null;
     		    String dipendenteCognome=null;
     		    String riderNome=null;
     		    String riderCognome=null;
     		       		   
     		   if(choise){
     		              System.out.println("Il ristorante ha affidato la consegna ad un servizio interno");
     		              
     		              String queryConsegnaDipendente= "select distinct dipendente.cf_dipendente, dipendente.nome, dipendente.cognome, cliente.id_cliente,consegna_dipendente.nominativo_ricevente "
     		   		                                    + "from dipendente "
     		   		                                    + "inner join consegna_dipendente on (dipendente.cf_dipendente=consegna_dipendente.cf_dipendente) "
     		   		                                    + "inner join cliente on (consegna_dipendente.id_cliente=cliente.id_cliente) "
     		   		                                    + "inner join ordine on(cliente.id_cliente=ordine.id_cliente) "
     		   		                                    + "where ordine.stato='espletato' and ordine.data1='"+data1+"' and cliente.nome='"+nome+"' and cliente.cognome='"+cognome+"';";	
     		   
     		   		      result= pquery.executeQuery(queryConsegnaDipendente);
     		   		    		   		 		   			
 		   			      while (result.next()){
 		   				                        cf_dipendente=result.getString("cf_dipendente");
 		   				                        idCliente=result.getString("id_cliente");
 		   				                        nominativo_ricevente=result.getString("nominativo_ricevente");
 		   				                        dipendenteNome=result.getString("nome");
 		   				                        dipendenteCognome=result.getString("cognome");
 		   				                        
 		   				                        System.out.println(cf_dipendente+" \t"+idCliente+" \t"+ nominativo_ricevente);
 		   				                       }
 		   					   			
 		   		          int upd = pquery.executeUpdate( "update dipendente "
 		   		 		                                + "inner join consegna_dipendente on (dipendente.cf_dipendente=consegna_dipendente.cf_dipendente) "
 		   		 		                                + "inner join cliente on (consegna_dipendente.id_cliente=cliente.id_cliente) "
 		   		 		                                + "inner join ordine on (cliente.id_cliente=ordine.id_cliente) "
 		   		 		                                + "set disponibilita=1 "
 		   		 		                                + "where dipendente.disponibilita=0 and  ordine.stato='espletato'  and ordine.data1='"+data1+"' and cliente.nome='"+nome+"' and cliente.cognome='"+cognome+"';");
 		   		 
 		   		          int insert= pquery.executeUpdate( "INSERT INTO consegna_dipendente(cf_dipendente, id_cliente, nominativo_ricevente, orario_effettivo, orario_presunto,data1)"
 	    		   		                                  + "select distinct '"+cf_dipendente+"','"+idCliente+"','"+nominativo_ricevente+"','"+orarioPresunto+"',NULL,'"+data1+"'"
 	    				                                  + "from consegna_dipendente "
 	    				                                  + "inner join cliente on (consegna_dipendente.id_cliente=cliente.id_cliente) "
 	    				                                  + "inner join ordine on (cliente.id_cliente=ordine.id_cliente) "
 	    				                                  + "where ordine.stato='espletato'  and ordine.data1='"+data1+"' and cliente.nome='"+nome+"' and cliente.cognome='"+cognome+"';");
 		   		
 		   		          int update=pquery.executeUpdate( "update dipendente "
 		   				                                 + "inner join consegna_dipendente on(dipendente.cf_dipendente=consegna_dipendente.cf_dipendente) "
 		   				                                 + "inner join cliente on(consegna_dipendente.id_cliente=cliente.id_cliente) "
 		   				                                 + "inner join ordine on(cliente.id_cliente=ordine.id_cliente) "
 		   				                                 + "set dipendente.disponibilita=0, ordine.stato='consegnato', consegna_dipendente.orario_effettivo=current_time()"
 		   				                                 + "where dipendente.disponibilita=1 and  ordine.stato='espletato'  and ordine.data1='"+data1+"' and cliente.nome='"+nome+"' and cliente.cognome='"+cognome+"';");
 		   		
 		   			
 		   		          if(update>1) System.out.println("Consegna effettuata da:"+dipendenteNome+ " "+dipendenteCognome);
 		    		      else  System.out.println("Non occorre consegnare nulla in questa data:"+data1+" a "+nome+" "+cognome);
 		   		         }
     		   else { 
     		   
     			     System.out.println("Il ristorante ha affidato la consegna ad un servizio esterno");
     			     
     		         String queryConsegnaRider= "select distinct rider.cf_rider, rider.nome, rider.cognome, cliente.id_cliente, consegna_rider.nominativo_ricevente"
     				                          + " from rider "
     		      		                      + "inner join consegna_rider on(rider.cf_rider=consegna_rider.cf_rider) "
     		      		                      + "inner join cliente on (consegna_rider.id_cliente=cliente.id_cliente) "
     		      		                      + "inner join ordine on (cliente.id_cliente=ordine.id_cliente) "
     		      		                      + "where ordine.stato='espletato' and ordine.data1='"+data1+"' and cliente.nome='"+nome+"' and cliente.cognome='"+cognome+"' and rider.disponibilita=0;";
     		   
     		   		 result = pquery.executeQuery(queryConsegnaRider);
     		   			
     		   		 while (result.next()){
     		   				               cf_rider=result.getString("cf_rider");
     		   				               idCliente=result.getString("id_cliente");
     		   				               nominativo_ricevente=result.getString("nominativo_ricevente");
     		   				               riderNome=result.getString("nome");
     		   				               riderCognome=result.getString("cognome");
     		   				               
     		   				               //System.out.println(cf_rider+" \t"+idCliente+" \t"+ nominativo_ricevente);
     		   				              }
     		   			
     		   		 int upd = pquery.executeUpdate( "update rider "
     		   		 		                       + "inner join consegna_rider on (rider.cf_rider=consegna_rider.cf_rider) "
     		   		 		                       + "inner join cliente on (consegna_rider.id_cliente=cliente.id_cliente) "
     		   		 		                       + "inner join ordine on (cliente.id_cliente=ordine.id_cliente) "
     		   		 		                       + "set disponibilita=1 "
     		   		 		                       + "where rider.disponibilita=0 and  ordine.stato='espletato'  and ordine.data1='"+data1+"' and cliente.nome='"+nome+"' and cliente.cognome='"+cognome+"';");
     		   		 
		             int insert= pquery.executeUpdate( "INSERT INTO consegna_rider(nominativo_ricevente, orario_presunto, orario_effettivo, score, data1, cf_rider, id_cliente)"
     		   		                                 + "select distinct '"+nominativo_ricevente+"','"+orarioPresunto+"',NULL,NULL,'"+data1+"','"+cf_rider+"','"+idCliente+"'" 
     				                                 + "from consegna_rider "
     				                                 + "inner join cliente on (consegna_rider.id_cliente=cliente.id_cliente) "
     				                                 + "inner join ordine on (cliente.id_cliente=ordine.id_cliente) "
     				                                 + "where ordine.stato='espletato' and ordine.data1='"+data1+"' and cliente.nome='"+nome+"' and cliente.cognome='"+cognome+"';");
     		   
                     int update=pquery.executeUpdate( "update rider "
     		   		                                + "inner join consegna_rider on(rider.cf_rider=consegna_rider.cf_rider) "
     		   		                                + "inner join cliente on (consegna_rider.id_cliente=cliente.id_cliente) "
     		   		                                + "inner join ordine on(cliente.id_cliente=ordine.id_cliente) "
     		   		                                + "set rider.disponibilita=0, ordine.stato='consegnato', consegna_rider.orario_effettivo=current_time() "
     		   		                                + "where rider.disponibilita=1 and  ordine.stato='espletato' and ordine.data1='"+data1+"' and cliente.nome='"+nome+"' and cliente.cognome='"+cognome+"';"); 
     		     
     		         if(update>1) System.out.println("Consegna effettuata da:"+riderNome+ " "+riderCognome);
     		         else  System.out.println("Non occorre consegnare nulla in questa data:"+data1+" a "+nome+" "+cognome);
     		        } 
     		   
           	}catch(Exception e) {
                   			     e.printStackTrace();
           	                    } 
        }
        
        public void query3(String nomeRistorante) {
            try {
                 String query = " SELECT ristorante.max_prenotazioni, COUNT(*) as coda_ordini"
                              + " FROM ordine inner join ristorante on (ordine.id_ristorante=ristorante.id_ristorante)"
                              + " WHERE ristorante.nome='"+nomeRistorante+"' and ordine.stato= 'ordinato' and ristorante.max_prenotazioni >= coda_ordini";  
                        
                 Statement pquery = con.createStatement();
                 ResultSet result = pquery.executeQuery(query);
                 
                 while (result.next()){										              
						               int maxPrenotazioni=result.getInt("max_prenotazioni");
						               int ordini=result.getInt("coda_ordini");
						
						               if(maxPrenotazioni>ordini) { 
							                          System.out.println("Disponibilita'  Ristorante "+nomeRistorante+" \t "+"Coda ordini del ristorante"+" \t "+"Numero massimo di prenotazioni del ristorante "+" \t ");
							                          System.out.println(" \t "+"Disponibile"+" \t "+" \t "+" \t "+" \t "+" \t "+" \t "+ ordini +" \t "+" \t "+" \t "+" \t "+" \t "+maxPrenotazioni+" \t ");
							                          System.out.println("Il ristorante ha disponibilita' ad espeltare un ordine");
							                                      }
						               
						               else System.out.println("Il ristorante non ha disponibilita' ad espletare un ordine");						
						              }    
                 
            	 }catch (Exception e) {
                                       e.printStackTrace();
                                      }
        }
        
        public void query4() {
        	try {
        		 String query= "SELECT ristorante.nome, ristorante.max_prenotazioni, ristorante.coda_ordini "
        			         + "FROM ristorante "
        			         + "where max_prenotazioni >( "
        			                                    + "SELECT COUNT(*) as coda_ordini "
        			                                    + "from ordine inner join ristorante on (ordine.id_ristorante=ristorante.id_ristorante) "
        			                                    + "WHERE  ordine.stato='ordinato' )";
        	     
        	     Statement pquery = con.createStatement();
        	     ResultSet result = pquery.executeQuery(query);
        	     
    				while (result.next()){
						                  String nome = result.getString("nome");
						                  int ordini = result.getInt("coda_ordini");						                  
						                  int maxPrenotazioni=result.getInt("max_prenotazioni");
						                  
						                  System.out.println("Disponibilita' Ristorante"+" \t "+nome+" \t "+"Coda ordini del ristorante"+" \t "+"Numero massimo di prenotazioni del ristorante "+" \t ");
						                  System.out.println(" \t "+" \t "+" \t "+" \t "+" \t "+" \t "+" \t "+" \t "+ ordini +" \t "+" \t "+" \t "+" \t "+" \t "+maxPrenotazioni+" \t ");
						                 }
        	   }catch(Exception e) {
        	                        e.printStackTrace();
                                   }
        }
        
     
        public void query5(String Nome, String Cognome, String nominativoRicevente, String data1, double score) {
        	try {
           	     Statement pquery = con.createStatement();
           	     
                 int update = pquery.executeUpdate( "update consegna_rider "
                		                          + "inner join rider on "
                		                          + "(consegna_rider.cf_rider=rider.cf_rider) inner join cliente on "
                		                          + "(consegna_rider.id_cliente=cliente.id_cliente) inner join ordine on "
                		                          + "(cliente.id_cliente= ordine.id_cliente) "
                		                          + "set consegna_rider.score='"+score+"' "
                		                          + "where consegna_rider.score is null and rider.nome='"+Nome+"' and rider.cognome='"+Cognome+"' and "
                		                          + "consegna_rider.nominativo_ricevente='"+nominativoRicevente+"' and ordine.stato='consegnato'and  consegna_rider.data1='"+data1+"';");
                              
                 int upd = pquery.executeUpdate( "update rider, consegna_rider "
                			                   + "set rider.score_medio=( "
                			                                            + "select  avg (consegna_rider.score) "
                			                                            + "from (select * from rider) as rider_temporaneo join consegna_rider on "
                			                                            + "(rider.cf_rider=consegna_rider.cf_rider) "
                			                                            + "where rider.nome='"+Nome+"' and rider.cognome='"+Cognome+"') "
                			                   + "where rider.nome='"+Nome+"' and rider.cognome='"+Cognome+"';");
                 
                if(update>=1) System.out.println("Valutazione inserita");
                
        	    }catch(Exception e){
                			        e.printStackTrace();
        	                       }
        }
        
        public void query6() {
        	try {        		 
        		 String query= "select cliente.nome, count(ordine.stato) as num_ordini "
        		 		     + "from cliente inner join ordine on (cliente.id_cliente=ordine.id_cliente) "
        		 		     + "group by nome ";
        		 
        		 Statement pquery = con.createStatement();
				 ResultSet result = pquery.executeQuery(query);
				
				 while (result.next()) {
						                String nome = result.getString("nome");
						                int ordini = result.getInt("num_ordini");
						                
						                System.out.println(nome+" \t "+ ordini +" \t ");
						               }
        	    }catch (Exception e) {
                                      e.printStackTrace();
                                     }
        }
        
        /*public void query6(String nomeCliente) {
        	try {
        		String query= "SELECT   nome, count(*)  as ordini "
        					+ "FROM  cliente inner join ordine on ordine.id_cliente=cliente.id_cliente "
        					+ "where nome='"+nomeCliente+ "' ";
        		Statement pquery = con.createStatement();
				ResultSet result = pquery.executeQuery(query);
				 if(result != null) 
					System.out.println("effettuato");
					else
					System.out.println(" non effettuato");
				 while (result.next()){
						String nome = result.getString("nome");
						String ordini = result.getString("ordini");
						System.out.println(nome+" \t "+ ordini +" \t ");
						}
        	}
        	catch (Exception e){
                e.printStackTrace();
            }
        }
        */
        
        public void query7(String nomeRistorante, String nomeSocieta) {
            
            String idRistorante=null;
            String idEsterno=null;
         
            try {
                 Statement pquery = con.createStatement();
                 String query= "select esterno.id_esterno, assegnato.p_iva "
                             + "from esterno inner join assegnato on (esterno.id_esterno=assegnato.id_esterno) "
                             + "inner join societadelivery on (assegnato.p_iva=societadelivery.p_iva) "
                             + "where societadelivery.nome='"+nomeSocieta+"'";
                  
                  ResultSet result = pquery.executeQuery(query);
                  while (result.next()){
                                        idEsterno= result.getString("id_esterno");
                                        String piva=result.getString("p_iva");
                                        System.out.println("Esterno:"+idEsterno+" \t"+piva);
                                       }
                  
                  String query2= "select ristorante.id_ristorante "
                               + "from ristorante "
                               + "where ristorante.nome='"+nomeRistorante+"';";
             
                  result = pquery.executeQuery(query2);  
                  while (result.next()){
                                        idRistorante = result.getString("id_ristorante");
                                        System.out.println("Ristorante:"+idRistorante+" \t");
                                       }
                          
                  int update = pquery.executeUpdate( "insert into affida_esterno(id_ristorante, id_esterno) "
                                                   + "select '"+idRistorante+"','"+ idEsterno +"'");
                 
               }catch(Exception e){
                                   e.printStackTrace();
                                  }
        }
        
        public void query8(String cf_dipendente, String nome, String cognome, String telefono, int anni_esperienza, String curriculum, String tipoContratto, String data1, int idInterno) {
        	try {
        	     Statement pquery = con.createStatement();
                 int result = pquery.executeUpdate("INSERT INTO dipendente(cf_dipendente, nome, cognome, telefono, anni_esperienza, curriculum, tipo_contratto, data_presa_servizio, id_interno) "
             		                             + "select '"+cf_dipendente+"',"+"'"+nome+"'"+",'"+cognome+"','"+telefono+"','"+anni_esperienza+"','"+curriculum+"','"+tipoContratto+"','"+data1+"','"+idInterno+"'");
                 
                 if(result==1) System.out.println("Assunzione effettuata correttamente");
                 else System.out.println("Errore nell'effettuare l'assunzione");
                 
        	    }catch(Exception e){ 
        	    	                e.printStackTrace();
    		                       }   		 		        		
        }
        
        public void query9() {
            try {            	 
                 String query= "select distinct ristorante.nome from ristorante inner join affida_interno on "
                             + "(ristorante.id_ristorante=affida_interno.id_ristorante) inner join affida_esterno on "
                             + "(ristorante.id_ristorante=affida_esterno.id_ristorante)inner join esterno on "
                             + "(affida_esterno.id_esterno=esterno.id_esterno) inner join assegnato on "
                             + "(affida_esterno.id_esterno=assegnato.id_esterno) inner join societadelivery on (assegnato.p_iva=societadelivery.p_iva)"
                             + "where societadelivery.nome='food delivery' or ristorante.id_ristorante=affida_interno.id_ristorante";
                 
                 Statement pquery = con.createStatement();
                 ResultSet result = pquery.executeQuery(query);
                 
                 while (result.next()){
                                       String nome = result.getString("nome");
                                       System.out.println(nome+" \t");
                                      }
                }catch(Exception e){
                                    e.printStackTrace();
                                   }      
        }
        
        public void query10() {
        	try {        		 
        		 String query="select ordine.*,consegna_rider.nominativo_ricevente,consegna_rider.score, consegna_rider.data1, consegna_rider.orario_presunto, consegna_rider.orario_effettivo "
        		 		     + "from ordine inner join cliente on "
        				     + "(ordine.id_cliente = cliente.id_cliente) inner join consegna_rider on "
        				     + "(cliente.id_cliente=consegna_rider.id_cliente) "
        				     + "where ordine.stato='consegnato' and  consegna_rider.score is NULL";
        		 
        		Statement pquery = con.createStatement();
                ResultSet result = pquery.executeQuery(query);
                
                while (result.next()){
                	                  int numeroGiornaliero=result.getInt("numero_giornaliero");
                	                  String data=result.getString("data1");
                	                  String tipo=result.getString("tipo");
                	                  String descrizione=result.getString("descrizione");
                	                  String stato=result.getString("stato");
                	                  int idCliente=result.getInt("id_cliente");
                	                  int idRistorante=result.getInt("id_ristorante");
                	                  String nominativoRicevente=result.getString("nominativo_ricevente");
                	                  String orarioPresunto=result.getString("orario_presunto");
                	                  String orarioEffettivo=result.getString("orario_effettivo");
                	                  double score=result.getDouble("score");
                	                  
                	                 System.out.println(numeroGiornaliero+" \t"+data+" "+tipo+"   "+descrizione+" \t"+stato+" \t"+idCliente+" \t"+idRistorante+" \t"+nominativoRicevente+" \t"+" "+orarioPresunto+" \t" +orarioEffettivo+" \t"+score+" \t");
                                    }
        	    }catch(Exception e){
        		                    e.printStackTrace();
        	                       }
        }
        
        public void query11(String idCliente, String descrizione, String numeroGiornaliero, String nomeRistorante) {
        	try {        		 
        		 String query= "SELECT id_cliente "
        		 		     + "from ordine "
        		 		     + "where ordine.id_cliente='"+idCliente+"' and ordine.stato='espletato' or ordine.stato='ordinato'"; 
        		 
        		Statement pquery = con.createStatement();
    			//ResultSet result = pquery.executeQuery(query);
    			Statement pquery1 = con.createStatement();
    			
    			int  result1 = pquery1.executeUpdate( "DELETE from ordine "
    					                            + "where id_cliente= '"+ idCliente +"' and ordine.descrizione='"+ descrizione +"'and ordine.numero_giornaliero='"+numeroGiornaliero +"'and (ordine.stato='ordinato'or ordine.stato='espletato')");
    			
    			int update= pquery.executeUpdate( "UPDATE ristorante "
                		                        + "set ristorante.coda_ordini = (SELECT distinct count(*) "
                		                                                      + "from (select * from ristorante) as ristorante_temporaneo join ordine on ordine.id_ristorante=ristorante_temporaneo.id_ristorante "
                		                                                      + "WHERE ordine.stato= 'ordinato' and ristorante_temporaneo.nome='"+nomeRistorante+"') "
                		                        + "WHERE ristorante.nome='" +nomeRistorante +"';");
    			
    			 if(result1>0) 
    				 System.out.println("Cancellazione effettuata correttamente");
                 else System.out.println("Errore nell'effettuare la cancellazione");
    			 
                }catch (Exception e){
                                     e.printStackTrace();
                                    }		       	
        }
        
        public void query12(){
        	try {       		 
        		 String query= "select distinct( concat(dipendente.nome,' ', dipendente.cognome)) as Nominativo "
        				     + "from ordine inner join cliente on "
        				     + "(ordine.id_cliente=cliente.id_cliente) inner join consegna_dipendente on "
        				     + "(cliente.id_cliente=consegna_dipendente.id_cliente) inner join dipendente on "
        				     + "(consegna_dipendente.cf_dipendente=dipendente.cf_dipendente) "
        				     + "where cliente.nome='Giuseppe' and cliente.cognome='Verdi' and ordine.stato='consegnato' and (ordine.data1 >= current_date() - INTERVAL DAYOFWEEK(current_date())+6 DAY  and ordine.data1<= current_date()) "
        				     + "union "
        				     + "(select distinct(concat(rider.nome,' ',rider.cognome)) as rider "
        				     + "from ordine  inner join cliente on "
        				     + "(ordine.id_cliente=cliente.id_cliente) inner join consegna_rider on "
        				     + "(cliente.id_cliente=consegna_rider.id_cliente) inner join rider on "
        				     + "(consegna_rider.cf_rider=rider.cf_rider) "
        				     + "where cliente.nome='Giuseppe' and cliente.cognome='Verdi' and ordine.stato='consegnato' and (ordine.data1 >= current_date() - INTERVAL DAYOFWEEK(current_date())+6 DAY  and ordine.data1<= current_date()));";
        		
        		Statement pquery = con.createStatement();
                ResultSet result = pquery.executeQuery(query);
                
                while (result.next()){
                	                  String nome = result.getString("nominativo");
                                      System.out.println(nome+" \t");
                	                 }
                
        	    }catch(Exception e) {
        			                 e.printStackTrace();
        		                    }
        }
        
        public void query13() {
        	try {        		 
        	     String query= "SELECT distinct ristorante.nome, ristorante.telefono, ristorante.max_prenotazioni, ristorante.coda_ordini, ristorante.via, ristorante.numero_civico, ristorante.cap, ristorante.citta "
        			         + "from ristorante";
        	     Statement pquery = con.createStatement();
			     ResultSet result = pquery.executeQuery(query);
			     
			     while (result.next()){
				                       String nome = result.getString("nome");
				                       String telefono=result.getString("telefono");
				                       int maxPrenotazioni=result.getInt("max_prenotazioni");
				                       int numeroCivico=result.getInt("numero_civico");
				                       String citta=result.getString("citta");
				                       int codaOrdini=result.getInt("coda_ordini");	
				 
				                      System.out.println("Nome Ristornate:  "+ nome +" \n"
				                      +"Telefono:  "+ telefono + "\n"
				                      +"Numero Massimo Prenotazioni:  "+ maxPrenotazioni +" \n"
				                      +"Numero civico:  "+ numeroCivico +"\n"
				                      +"Citta':  "+citta+"\n"
				                      +"Coda Ordini:  "+codaOrdini+"\n"
				                      +"\n");
				                     
				                     }
        	   }catch(Exception e){
        		                   e.printStackTrace();
        	                      }
        }
       
        public void query14() {
        	try{
        		 
        		String query= "SELECT * "
        				     + "FROM rider;";
        		
        		Statement pquery = con.createStatement();
                ResultSet result = pquery.executeQuery(query);
                
                while (result.next()){
                	                  String cf=result.getString("cf_rider");
                                      String nome = result.getString("nome");
                                      String cognome=result.getString("cognome");
                                      String telefono=result.getString("telefono");
                                      String dataPrimoImpiego=result.getString("data_primo_impiego");
                                      double scoreMedio=result.getDouble("score_medio");
                                      String disponibilita=result.getString("disponibilita");
                                      String targa=result.getString("targa");
                                      String veicolo=result.getString("veicolo");
                    
                                      System.out.println(cf+" \t"+nome+" "+cognome+"   "+telefono+" \t"+dataPrimoImpiego+" \t"+scoreMedio+" \t"+disponibilita+" \t"+targa+" \t"+" "+veicolo+" \t");
                                     }
            }catch(Exception e){
                                e.printStackTrace();
                               }
       }
            
       public void query15(){
        	try {        		 
        		 String query= "SELECT  cliente.id_cliente,cliente.nome,cliente.cognome,cliente.telefono,cliente.via,cliente.citta,consegna_rider.score,rider.score_medio,consegna_rider.data1 "
        				     + "FROM cliente inner join consegna_rider on cliente.id_cliente= consegna_rider.id_cliente "
        				     + "inner join rider on consegna_rider.cf_rider=rider.cf_rider "
        				     + "where consegna_rider.data1 >= current_date() - INTERVAL DAYOFWEEK(current_date())+6 DAY  and consegna_rider.data1<= current_date() and score_medio>= score";
        		 
        		Statement pquery = con.createStatement();
                ResultSet result = pquery.executeQuery(query);
                
                while (result.next()){
                	                  int idCliente=result.getInt("id_cliente");
                	                  String nome = result.getString("nome");
                                      String cognome=result.getString("cognome");
                                      String telefono=result.getString("telefono");
                                      String via=result.getString("via");
                                      String citta=result.getString("citta");
                                      double score=result.getDouble("score");
                                      double scoreMedio=result.getDouble("score_medio");
                                      String data=result.getString("data1");
                                     
                                      System.out.println(idCliente+" \t"+nome+" "+cognome+"   "+telefono+" \t"+via+" \t"+citta+" \t"+score+" \t" +scoreMedio+ " \t"+data+" \t");
                	                 }
        		 }catch(Exception e) {
        			                 e.printStackTrace();
        		                     }
        }
       
       
       public void menu() {
    	  System.out.println( "\n"
    			  			+ "\n"
    			  			+ "1 - Registrazione di un ordine\n"
                            + "2 - Consegna di Un ordine\n"
                            + "3 - Verifica della possibilità di effettuare un ordine ad un determinato ristorante\n"
                            + "4 - Visualizzazione dei ristoranti disoponibili all'accettazione di un ordine\n"
                            + "5 - Valutazione di un rider\n"
                            + "6 - Visualizzazione per ogni cliente del numero di ordini effettuati\n"
                            + "7 - Abilitazione dell’affidamento ad una società di un servizio di delivery (già presente)\n"
                            + "8 - Assunzione di un nuovo dipendente per la consegna degli ordini\n"
                            + "9 - Visualizzazione dei nomi dei ristoranti che impiegano dipendenti propri per la consegna o che si affidano ai servizi della società «Food Delivery»\n"
                            + "10 - Visualizzazione degli ordini consegnati da Raider ancora non valutati\n"
                            + "11 - Cancellazione di un ordine ancora non consegnato\n"
                            + "12 - Stampa di tutte le persone (nome, cognome) che abbiano consegnato ordini a «Giuseppe Verdi» nell’ultima settimana\n"
                            + "13 - Stampa di un report che mostri i dati dei ristoranti, incluso la coda di ordini attuale\n"
                            + "14 - Stampa settimanale di un report che mostri i dati dei rider, incluso lo score medio ottenuto nelle valutazioni da parte dei clienti\n"
                            + "15 - Stampa settimanale di tutti i clienti che nell’ultima settimana abbiano effettuato almeno una valutazione inferiore al corrispondente score medio di un rider\n"                 
                            + "Per terminare l'esecuzione digitare 0"
                            +"\n"
                            +"\n"
                            );
                          }

      private Connection con=null;       
}