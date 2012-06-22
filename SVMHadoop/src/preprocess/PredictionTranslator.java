package preprocess;

import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;

public class PredictionTranslator {
	
	public static void translateDir(File in, File outdir, int dim) throws IOException {
		File[] subfiles = in.listFiles();
		for(int i = 0 ; i < subfiles.length ; i++) {
			translate(subfiles[i], outdir, dim);
		}
	}
	
	/**
	 * 
	 * translate the binary prediction file into a plain text file
	 * @param in the input 
	 * @param outdir the output dir for the text file 
	 * @param dim the dimension of prediction (346)
	 * @throws IOException
	 */
	public static void translate(File in, File outdir, int dim) throws IOException {
		BufferedWriter bw = new BufferedWriter(new FileWriter(new File(outdir,in.getName()+".txt")),1024*8*16);
		DataInputStream br = new DataInputStream(new FileInputStream(in));
		while(br.available() > 0) {
			for(int i = 0 ; i < dim+1 ; i ++) {
				double prediction = br.readDouble();
				bw.write(prediction + " ");
			}
			bw.write("\r\n");
		}
		bw.flush();
		bw.close();
	}
	
	
	public static void main(String[] args) throws Exception {
		
		translateDir(new File(args[0]), new File(args[1]), Integer.parseInt(args[2]));

	}

}
