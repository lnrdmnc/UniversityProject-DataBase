
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.sql.*;
import java.sql.Date;
import java.util.*;
import java.util.Date.*;

    public class QueryTest {
    	 
       	public static int generaCodiceRistorante(String nomeRistorante){
    	    int idRistorante=0;
    	   
    	   	if(nomeRistorante.compareTo("la tavernetta")==0)        idRistorante=78;
    	   
    	   	if(nomeRistorante.compareTo("osteria nonna maria")==0)  idRistorante=25;
    	   
    	 	if(nomeRistorante.compareTo("scacciapensier")==0)       idRistorante=86;
    	   
    	    return idRistorante;   		   
      }
       	
       	public static boolean esisteRistorante(String nomeRistorante){
     	    boolean esiste=false;
     	   
     	   	if(nomeRistorante.compareTo("la tavernetta")==0)        esiste=true;
     	   
     	   	if(nomeRistorante.compareTo("osteria nonna maria")==0)  esiste=true;
     	   
     	 	if(nomeRistorante.compareTo("scacciapensier")==0)       esiste=true;
     	   
     	    return esiste;
     		   
     }
       	
    	public static boolean clienteExist(String nome, String cognome){
      	  boolean exist=false;
      	   
      	   if(nome.compareTo("Massimo")==0)
      	   		if(cognome.compareTo("Massi")==0)
      	   			exist=true;
      		   
      	   if(nome.compareTo("Algero")==0)
     	   		if(cognome.compareTo("Capozzoli")==0)
     	   			exist=true;
      	   
      	   if(nome.compareTo("Teodoro")==0)
    	   		if(cognome.compareTo("Capozzi")==0)
    	   			exist=true;
      	  
      	   if(nome.compareTo("Antonio")==0)
   	   		    if(cognome.compareTo("Bidone")==0)
   	   			    exist=true;
      	 
      	   if(nome.compareTo("Giuseppe")==0)
  	   		    if(cognome.compareTo("Verdi")==0)
  	   			    exist=true;
      	   
      	  return exist;    		   
      }     
       	
       	public static int generaCodiceCliente(String nome, String cognome){
     	    int idCliente=0;
     	   
     	   	if(nome.compareTo("Massimo")==0)
     	   		if(cognome.compareTo("Massi")==0)
     	   			idCliente=1134;
     		   
     	   
     	    if(nome.compareTo("Algero")==0)
    	   		if(cognome.compareTo("Capozzoli")==0)
    	   			idCliente=1727;
     	   
     	    if(nome.compareTo("Teodoro")==0)
   	   		   if(cognome.compareTo("Capozzi")==0)
   	   			    idCliente=1543;
     	  
     	    if(nome.compareTo("Antonio")==0)
  	   		   if(cognome.compareTo("Bidone")==0)
  	   			    idCliente=1264;
     	 
     	   if(nome.compareTo("Giuseppe")==0)
 	   		   if(cognome.compareTo("Verdi")==0)
 	   			    idCliente=1000;
     	   
     	   return idCliente;    		   
     }
       	
       	public static boolean riderEsiste(String nome,String cognome) {
       		boolean exist=false;
       	   
      	   	if(nome.compareTo("Antonio")==0)
      	   		if(cognome.compareTo("Cassano")==0)
      	   			exist=true;
      		   
      	   
      	    if(nome.compareTo("Pietro")==0)
     	   		if(cognome.compareTo("Panno")==0)
     	   			exist=true;
      	   
      	    if(nome.compareTo("Marco")==0)
    	   		if(cognome.compareTo("Larso")==0)
    	   			exist=true;

            return exist;
       	}       	
       	
    	public static boolean esisteSocieta(String nome){
    		boolean esiste=false;
    		
     	   	if(nome.compareTo("just eat")==0)        esiste=true;
     	   		
     	   	if(nome.compareTo("food delivery")==0)   esiste=true;
     	   		
     	   	if(nome.compareTo("glovo")==0)	         esiste=true;
     	   	 
     	    if(nome.compareTo("toro mariconda")==0)  esiste=true;
     	   
     	    if(nome.compareTo("alfonsino")==0)       esiste=true;
     	  
     	    return esiste;
    }       
          
    	public static String generaCodiceFiscale() {
    		Scanner in=new Scanner(System.in);
    		String cf=null;
    		
    		do{
    			cf=in.next();
    			if(cf.length()!=16) System.out.println("Codice Fiscale errato");
    		}
    		while(cf.length()!=16);
    		
    		return cf;
        }
    	
    	public static String generaTelefono() {
    		Scanner in=new Scanner(System.in);
    		String telefono=null;
    		
    		do{
    		System.out.println("Inserire numero di telefono non dimenticare il prefisso:(+39):");
    		telefono=in.next();
    			if(telefono.length() != 13) System.out.println("Numero di telefono errato");
    		}
    		while(telefono.length()!=13);
    		
    		return telefono;
    		
    	}
    	
    	public static boolean datainferiore(java.util.Date date1, java.util.Date date2){
    			
    		if(date1.before(date2)) return true;
    			else  return false;
	
    	}
    	
    	public static int numeroG(boolean reset, int numeroGio) {
    		if(reset) 
    			numeroGio=0;
    		//else
    			//numeroGio++;
    		
    		return numeroGio;   				
    	}
    	
    	public static void main(String args[])throws Exception  {
            	Date date2 = new Date(System.currentTimeMillis());
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                
                Scanner in=new Scanner(System.in);
                String numeroGiornaliero;
                String idRistorante;
                String idCliente;
               
                System.out.println("Inserisci data (gg/mm/yyyy): ");
                String myDateStr=in.next();
                       
                java.util.Date date1=null;
                java.util.Date current=new Date(System.currentTimeMillis());
                SimpleDateFormat sd = new SimpleDateFormat("dd-MM-yyyy");
                       
                DateFormat formatoData = DateFormat.getDateInstance(DateFormat.SHORT, Locale.ITALY);
                          
                formatoData.setLenient(false);           
                date1 = formatoData.parse(myDateStr);                   
                boolean reset=datainferiore(date1,current);
                           
                numeroGio=numeroG(reset,numeroGio);
                numeroGiornaliero=Integer.toString(numeroGio);
                       
                Query T= new Query();
                T.connessione();                     
 
                int scelta;
             do{
                T.menu();
                System.out.println("Scegli operazione: ");
                scelta=in.nextInt();
                in.nextLine();
                    
                switch(scelta){
                case 1:{
                 		numeroGio++;
                 		numeroGiornaliero=Integer.toString(numeroGio);	
                 		
                 		System.out.println("Inserisci l'ordine (primo, secondo, dessert, menu completo ...):");
                 		String tipo=in.next(); 
                 		
                 		if(!(tipo.equalsIgnoreCase("primo") || tipo.equalsIgnoreCase("secondo") || tipo.equalsIgnoreCase("dessert") || tipo.equalsIgnoreCase("menu completo")))
                 			 throw new Exception("il tipo non corrisponde ai tipi consentiti");
                  		
                 																						
                 		System.out.println("Inserisci descrizione:");
                 		String descrizione=in.next();
                 																					
                 		System.out.println("Inserire il nome del ristorante che deve espletare l'ordine");
                 		System.out.println();
                 		in.nextLine();
                 		String nomeRistorante=in.nextLine();
                 																							                		
                 		int ristoranteId=generaCodiceRistorante(nomeRistorante);
                 		
                 		if(ristoranteId==0) throw new Exception("Ristorante inesistente");
                 		else idRistorante=Integer.toString(ristoranteId);
                 			                			
                 		System.out.println("Inserire nome cliente");
                 		String nome=in.next();
                 		
                 		System.out.println("Inserire cognome cliente");
                 		String cognome=in.next();                 		                		
                 		
                 		int clienteId=generaCodiceCliente(nome,cognome);
                 		
                 		if(clienteId==0)  throw new Exception("Il cliente non risulta nel nostro database");                			
                 		else  idCliente=Integer.toString(clienteId);
                 			
                 		T.query1(nomeRistorante, numeroGiornaliero, date2, tipo, descrizione, "ordinato", idCliente, idRistorante);		
                 		break;
                 	   }
                
                case 2:{                		
                	    System.out.println("Inserire il nome del ricevente");
             		    String nome=in.next();
             		
             		    System.out.println("Inserire il cognome ");
             		    String cognome=in.next();
             		    
                 		boolean esiste=clienteExist(nome,cognome);
                 		
                 		if(!esiste) throw new Exception("Cliente inesistente");
                 		
                 		System.out.println("Inserisci un orario (hh:mm:ss)");
                 		String orario=in.next();
                 		
                 		System.out.println("Inserisci data (yyyy/mm/gg) ");
                 		String dateConsegna=in.next();
                 		                		
                 		System.out.println("Consegna tramite dipendente (true), tramite rider (false)");
                 		boolean choise=in.nextBoolean();
                 		
                 		T.query2(nome, cognome, choise,dateConsegna , orario);
                 		
                 		break;
                 	   }
                
               case 3:{
            	       System.out.println("Inserisci nome ristorante");
            		   String nomeRistorante=in.nextLine();
            		
            		   boolean esiste=esisteRistorante(nomeRistorante);
            		
            		   if(!esiste)  throw new Exception("Ristorante inesistente");
            		
            		   T.query3(nomeRistorante);
            		
            		   break;
            	      }
               
               case 4:{
            		   System.out.println("Ristoranti disponibili all'accettazione di un ordine:");                 		
                       T.query4();                        
                       break;                       
            	      }
               
               case 5:{
            		   double score;
            		
            		   System.out.println("Inserisci il nome del rider");
            		   String riderName=in.next();
            		
            		   System.out.println("Inserisci il cognome");
            		   String riderCognome=in.next();
            		
            		   boolean esiste=riderEsiste(riderName,riderCognome);
            		
            		   if(!esiste)  throw new Exception("Rider inesistente");
            		
            		   System.out.println("Inserisci una data valida (yyyy/mm/gg) per valutare un rider");
            		   String data=in.next();
            		
            		   System.out.println("Inserisci il nominativo del ricevente");
            		   in.nextLine();
            		   String ricevente=in.nextLine();
            		   
            		   do{
            		     System.out.println("Inserisci un valutazione compresa tra 0 e 5");
            		     score=in.nextDouble();
            		     } while(score<0 && score>5);
            		
           		       T.query5(riderName, riderCognome, ricevente, data,score);
           		
           		       break;	
            	      }
               
               case 6:{
            		   System.out.println("Numero di ordini effettuati da ogni cliente:\n");
                       T.query6();
                       break;                		
            	      }
               
               case 7:{
            		   System.out.println("Inserisci il nome del Ristorante a cui si vuole abilitare l’affidamento di una società di un servizio di delivery");
            		   System.out.println();
            		   String nomeRistorante=in.nextLine();
            		
            		   boolean esiste=esisteRistorante(nomeRistorante);
            		
            		   if(!esiste)  throw new Exception("Ristorante inesistente");
            		
            		   System.out.println("Inserisci il nome della società");
            		   String nomeSocieta=in.nextLine();
            		
            		   esiste=esisteSocieta(nomeSocieta);
            		
            		   if(!esiste)  throw new Exception("Società inesistente");
            		
            		   T.query7(nomeRistorante, nomeSocieta);
            		
            		   break;                 		
            	      }
               
               case 8:{
            	       System.out.println("Inserisci codice fiscale");
            	       String cf=generaCodiceFiscale();
            	       System.out.println(cf);
            	    
            	       System.out.println("Inserisci nome");
        	           String nome= in.next(); 
        	        
        	           System.out.println("Inserisci cognome");
        	           String cognome= in.next();
        	        
            	       System.out.println("Inserisci numero di telefono");
            	       String telefono=generaTelefono();
            	   
            	       System.out.println("Anni di esperienza");
            	       int anniEsperienza=in.nextInt();
            	   
            	       System.out.println("Inserisci curriculum");
            	       in.nextLine();
            	       String curriculum=in.nextLine();
            	   
            	       System.out.println("Tipo contratto");
            	       String tipo=in.next();
            	   
            	       System.out.println("Inserisci data di presa servizio (yyyy/mm/gg)");
            	       String dataPS=in.next();
            	   
            	       System.out.println("Inserisci id interno tra 15,34,66");
            	   
            	       int idInterno=in.nextInt();
            	   
            	       if(idInterno== 15 || idInterno==34 || idInterno==66) System.out.println("Id interno corretto");                 		
            	       else  throw new Exception("Id inesistente");
            	    
            	       T.query8(cf, nome, cognome, telefono, anniEsperienza, curriculum, tipo, dataPS, idInterno);
            	    
            	       break;	
            	      }
               
               case 9:{
                       System.out.println("I ristoranti che impiegano dipendenti propri per la consegna o che si affidano ai servizi della società «Food Delivery»:\n");
                       T.query9();
                       break;                	
            	      }
           
              case 10:{
            		   System.out.println("Ordini consegnati da Rider ancora non valutati:");
                       T.query10();
                       break;	
            	      }
                 	                	
              case 11:{
                 	   System.out.println("Inserisci il numero dell'ordine che vuoi cancellare");
                 	   String numeroGiornalier=in.next();
                 	   
                 	   System.out.println("Inserisci nome");
                 	   String nome= in.next(); 
                 	   
                 	   System.out.println("Inserisci cognome");
                 	   String cognome= in.next();
                 	   
                 	   int clienteId=generaCodiceCliente(nome,cognome);
                 	   
                 	   idCliente=Integer.toString(clienteId);
                 	   
                 	   System.out.println("Inserisci descrizione");
                 	   String descrizione=in.next();
                 	                    	   
                 	   System.out.println("Inserisci il nome del ristorante");
                 	   in.nextLine();
                 	   
                 	   String nomeRistorante=in.nextLine();
                 	                    	
                 	   boolean esiste=esisteRistorante(nomeRistorante);
                 	   
                 	   if(!esiste)  throw new Exception("Ristorante inesistente");
                 	   
                 	   T.query11(idCliente,descrizione, numeroGiornalier, nomeRistorante);
                 	   break;
                 	  }
              
              case 12:{
                 	   System.out.println("Persone che hanno consegnato ordini a «Giuseppe Verdi» nell’ultima settimana");
                       T.query12();
                 	   break;
                 	  }
              
              case 13:{
                 	   System.out.println("Report che mostra i dati dei ristoranti, incluso la coda di ordini attuale");
                       T.query13();
                       break;
                 	  }
              
              case 14:{
                 	   System.out.println("Report settimanale che mostra i dati dei rider, incluso lo score medio ottenuto nelle valutazioni da parte dei clienti");
                       T.query14();
                       break;
                 	  }
              
              case 15:{
                 	   System.out.println("Clienti che nell’ultima settimana hanno effettuato almeno una valutazione inferiore al corrispondente score medio di un rider");
                       T.query15();
                       break;
                 	  }                	
                 	}                 
                }while(scelta!= 0);          
                 	System.out.println("");
    	}
    private static int numeroGio=0;	
   }
