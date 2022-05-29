
package runlengthcompresser;

import model.Compressor;
import model.Decompressor;

/**
 *
 * @author mateus
 */
public class RunLengthCompresser {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       
        Compressor compressor =  Compressor
                                .Configure()
                                .PathFileOutput("/home/mateus/Desktop")
                                .SetMatrizDimension(25)
                                .SetNumberOfDiffColors(3).Build();
        
        Decompressor decompressor = Decompressor.Configure(compressor).Build();
        
        decompressor.showImage();
                                
    }
    
}
