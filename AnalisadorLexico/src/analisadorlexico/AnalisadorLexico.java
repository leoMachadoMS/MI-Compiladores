package analisadorlexico;

import Model.Lexer;
import Model.Token;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Leonardo e Luiz
 */
public class AnalisadorLexico {

    private Iterable<String> code;
    private ArrayList<Token> tokens;
    private ArrayList<Token> errors;

    /**
     * @param args the command line arguments
     * @throws java.io.FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {
        ArrayList<String> code = new ArrayList<>();

        File inputFolder = new File("data/input");

        for (File f : inputFolder.listFiles()) {
            if (!f.isFile()) {
                continue;
            }
            
            String filename = f.getName();
            String outputFilename = "saida_" + filename;
            if(filename.startsWith("entrada")){
                outputFilename = "saida" + filename.substring(7);
            }
            
            code.clear();

            System.out.println("Processando arquivo " + filename);

            BufferedWriter writer;
            try (BufferedReader reader = new BufferedReader(new FileReader("data/input/" + filename))) {
                writer = new BufferedWriter(new FileWriter("data/output/" + outputFilename, false));

                for (String line; (line = reader.readLine()) != null;) {
                    code.add(line);
                }
                int errors = 0;
                Lexer lexer = new Lexer(code);
                for (Token token : lexer.getTokens()) {
                    if(token.isError())
                        errors++;
                    writer.write(token.toString() + "\r\n");

                }
                writer.write("\n");
                /*boolean noerrors = true;
                for (Token error : lexer.getErrors()) {
                    writer.write(error.toString() + "\r\n");
                    noerrors = false;
                }*/
                
                if (errors == 0) {
                    writer.write("Sem Erros");
                }else{
                    writer.write("O Arquivo tem " + errors + " erros");
                }

                writer.flush();
                writer.close();
                
            }

        }
    }

}
