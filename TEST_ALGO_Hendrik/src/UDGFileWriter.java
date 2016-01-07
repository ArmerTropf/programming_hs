import java.io.FileWriter;
import java.io.IOException;

public class UDGFileWriter {

    private FileWriter fileWriter;

    /**
     * Creates a new file in the given location with the given name,
     *      or opens an existing file, to write into.
     * @param path Path of the file to be written into.
     * @param filename Name of the file to be written into. file extension will be added if not given.
     * @throws IOException Throws exception, if file cannot be opened or created for some reason.
     */
    public UDGFileWriter(String path, String filename) throws IOException {
        // check for right extension
        if(!filename.endsWith(".udg"))
            filename+= ".udg";

        // check for tailing (back-)slash
        if(!path.endsWith("\\") && !path.endsWith("/"))
            path+= "/";

        String file = path+filename;

        // open file
        fileWriter = new FileWriter(file, false);

    }

    public void write(String term) throws IOException {
        fileWriter.write(term);
    }

    public void writeTerm(TermBuilder termBuilder) throws IOException {
        fileWriter.write(termBuilder.getTerm());
    }

    public void close() throws IOException {
        fileWriter.close();
    }

}