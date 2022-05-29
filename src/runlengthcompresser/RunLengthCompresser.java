
package runlengthcompresser;

import model.Compressor;
import model.Decompressor;


public class RunLengthCompresser {

    public static void main(String[] args) {
       

        Compressor compressor =  Compressor
                                .Configure()
                                .PathFileOutput("/home/username/Desktop")
                                .SetMatrizDimension(25)
                                .SetNumberOfDiffColors(5).Build();
                              
        
        Decompressor decompressor = Decompressor.Configure(compressor).Build();
        
        decompressor.showImage();
                                
    }
    
}
