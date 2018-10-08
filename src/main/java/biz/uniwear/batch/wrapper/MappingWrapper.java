package biz.uniwear.batch.wrapper;

import biz.uniwear.batch.wrapper.MappingConfig;

import java.util.List;

public class MappingWrapper {


    public void main(MappingConfig mappingConfig) {
        String mappingClass = mappingConfig.getMapping();
        List inargs = mappingConfig.getInargs();
        List outargs = mappingConfig.getInargs();

        // try {
        //     Class<?> aClass = Class.forName(mappingClass);

        // } catch (ClassNotFoundException e) {
        //     try {
        //         System.out.println(substring(0, fileFullPath.lastIndexOf('.'))mappingClass);
        //         Class<?> aClass = Class.forName(mappingClass);

        //     } catch (ClassNotFoundException ex) {
        //         ex.printStackTrace();
        //     }
        // }


        ((List) inargs).forEach(System.out::println);
        ((List) outargs).forEach(System.out::println);

        return;

    }
}

// 		try { // Mapping
// 			TraceTargetConsole ttc = new TraceTargetConsole();


// 			MappingMapTouniwear MappingMapTouniwearObject = new MappingBuilder().getMapper()
// 			//java.sql.DriverManager.setLogWriter(new java.io.PrintWriter(java.lang.System.err));


// 			Class.forName("org.mariadb.jdbc.Driver");

// 			MappingMapTouniwearObject.registerTraceTarget(ttc);


// 			// run mapping
// 			//
// 			// you have different options to provide mapping input and output:
// 			//
// 			// files using file names (available for XML, text, and Excel):
// 			//   com.altova.io.FileInput(String filename)
// 			//   com.altova.io.FileOutput(String filename)
// 			//
// 			// streams (available for XML, text, and Excel):
// 			//   com.altova.io.StreamInput(java.io.InputStream stream)
// 			//   com.altova.io.StreamOutput(java.io.OutputStream stream)
// 			//
// 			// strings (available for XML and text):
// 			//   com.altova.io.StringInput(String xmlcontent)
// 			//   com.altova.io.StringOutput()	(call getContent() after run() to get StringBuffer with content)
// 			//
// 			// Java IO reader/writer (available for XML and text):
// 			//   com.altova.io.ReaderInput(java.io.Reader reader)
// 			//   com.altova.io.WriterOutput(java.io.Writer writer)
// 			//
// 			// DOM documents (for XML only):
// 			//   com.altova.io.DocumentInput(org.w3c.dom.Document document)
// 			//   com.altova.io.DocumentOutput(org.w3c.dom.Document document)
// 			// 
// 			// By default, run will close all inputs and outputs. If you do not want this,
// 			// call the following function:
// 			// MappingMapTouniwearObject.setCloseObjectsAfterRun(false);

// 			{
// 				com.altova.io.Input InventorySPI2Source = com.altova.io.StreamInput.createInput("schema/InventorySPI.csv");

// 				MappingMapTouniwearObject.run(
// 						InventorySPI2Source,
// 						com.altova.db.Dbs.newConnection(
// 							"jdbc:mariadb://127.0.0.1/uniwear",
// 							"root",
// 							"root"));


// 			}


// 			System.err.println("Finished");
// 		} 
// 		catch (com.altova.UserException ue) 
// 		{
// 			System.err.print("USER EXCEPTION:");
// 			System.err.println( ue.getMessage() );
// 			System.exit(1);
// 		}
// 		catch (com.altova.AltovaException e)
// 		{
// 			System.err.print("ERROR: ");
// 			System.err.println( e.getMessage() );
// 			if (e.getInnerException() != null)
// 			{
// 				System.err.print("Inner exception: ");
// 				System.err.println(e.getInnerException().getMessage());
// 				if (e.getInnerException().getCause() != null)
// 				{
// 					System.err.print("Cause: ");
// 					System.err.println(e.getInnerException().getCause().getMessage());
// 				}
// 			}
// 			System.err.println("\nStack Trace: ");
// 			e.printStackTrace();
// 			System.exit(1);
// 		}

// 		catch (Exception e) {
// 			System.err.print("ERROR: ");
// 			System.err.println( e.getMessage() );
// 			System.err.println("\nStack Trace: ");
// 			e.printStackTrace();
// 			System.exit(1);
// 		}
// }
// }


// class TraceTargetConsole implements com.altova.TraceTarget {
// 	public void writeTrace(String info) {
// 		System.err.println(info);
// 	}
// }


// //Class.forName("org.mariadb.jdbc.Driver");


    // /**
    //  * Get Class by name via Class.forName(String).
    //  * @param name Class name.
    //  * @return Class|null
    //  */
    // private static Class<?> getClassByName(String name) {
    //     try {
    //         // Retrieve class by name.
    //         return Class.forName(name);
    //     } catch (ClassNotFoundException exception) {
    //         // Output exception ClassNotFoundExceptions.
    //         Logging.log(exception);
    //     } catch (Exception exception) {
    //         // Output unexpected Exceptions.
    //         Logging.log(exception, false);
    //     }
    //     return null;
    // }