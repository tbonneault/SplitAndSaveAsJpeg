# SplitAndSaveAsJpeg Plugin for ImageJ

## Overview

The `SplitAndSaveAsJpeg` plugin for ImageJ processes TIFF images in a selected directory, converts them to JPEG format, and saves them in dedicated folders. If the TIFF images contain multiple channels, each channel is saved as a separate JPEG file.

## Features

- Converts TIFF images to JPEG format.
- Splits multi-channel TIFF images into separate JPEG files for each channel.
- Organizes output files into dedicated folders for each original TIFF image.

## Installation

1. Download the `SplitAndSaveAsJpeg.java` file.
2. Place the file in the `plugins` folder of your ImageJ installation directory.
3. Restart ImageJ to load the new plugin.

## Usage

1. Open ImageJ.
2. Navigate to `Plugins` > `SplitAndSaveAsJpeg`.
3. Select the folder containing your TIFF images.
4. The plugin will process each TIFF image, convert it to JPEG, and save the output in a dedicated folder within the selected directory.

## Requirements

- ImageJ or Fiji installation.
- Java runtime environment.

## License

This plugin is licensed under the [GPL v3.0 License](LICENSE).

## Contributing

Contributions are welcome! Please open an issue or submit a pull request with your proposed changes.

## Contact

For any questions or issues, please open an issue on this repository or contact the maintainer directly.

---

Feel free to customize the `README.md` further to better fit your project's specific needs or add additional sections as necessary.
