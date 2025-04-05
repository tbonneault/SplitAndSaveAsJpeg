import ij.*;
import ij.io.*;
import ij.process.*;
import ij.plugin.*;
import ij.plugin.frame.*;
import java.io.*;

public class SplitAndSaveAsJpeg implements PlugIn {

    @Override
    public void run(String arg) {

        // Open a directory chooser to select the folder containing TIFF images
        DirectoryChooser dirChooser = new DirectoryChooser("Select a folder containing TIFF images");
        String inputDir = dirChooser.getDirectory();
        // Check the selected directory
        if (inputDir == null) return;
        
        // Get all TIFF files from the selected directory
        File folder = new File(inputDir);
        File[] listOfFiles = folder.listFiles((dir, name) -> name.toLowerCase().endsWith(".tif") || name.toLowerCase().endsWith(".tiff"));
        
        // Check if there are any TIFF images in the folder
        if (listOfFiles == null || listOfFiles.length == 0) {
            IJ.showMessage("No TIFF images found in the selected folder.");
            return;
        }
        
        // Process each TIFF image in the folder
        for (File file : listOfFiles) {
            // Open the image
            ImagePlus imp = new Opener().openImage(file.getAbsolutePath());
            // Check the image opening
            if (imp == null) continue;
            
            // Extract the base name of the file without the extension
            String baseName = file.getName().replaceAll("\\.tif$|\\.tiff$", ""); // Handle both .tif and .tiff
            
            // Create a dedicated folder for the image inside the main directory
            String imageOutputDir = inputDir + File.separator + baseName;
            File outputDir = new File(imageOutputDir);
            // Check if the folder exists
            if (!outputDir.exists()) {
                outputDir.mkdirs(); // Create folder for the image
            }
            
            // Save the original image as a JPEG file in its dedicated folder
            String outputPath = imageOutputDir + File.separator + baseName + ".jpg";
            IJ.saveAs(imp, "Jpeg", outputPath);
            
            // Check if the image has multiple channels and split them
            if (imp.getNChannels() > 1) {
                // Split the image channels
                ImagePlus[] channels = ChannelSplitter.split(imp);
                // Process each channels 
                for (int i = 0; i < channels.length; i++) {
                    // Get the name of the channel assigned by ImageJ
                    String channelName = channels[i].getTitle();
                    
                    // Remove the ".tiff" or ".tif" from the channel name and add ".jpeg"
                    String channelBaseName = channelName.replaceAll("\\.tif$|\\.tiff$", "");                    
                    String newFileName = channelBaseName + ".jpg";
                    
                    // Save the channel as a separate JPEG file
                    String channelOutputPath = imageOutputDir + File.separator + newFileName;
                    IJ.saveAs(channels[i], "Jpeg", channelOutputPath);
                }
            }
            
            // Close the image to free memory
            imp.close();
        }
        
        // Show a message when processing is complete
        IJ.showMessage("Conversion Completed!", "All TIFF images have been converted to JPEG and saved in their respective folders.");
    }
}
