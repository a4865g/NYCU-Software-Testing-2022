#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int bmpConvert(char *input_name, char *output_name)
{
    unsigned char pixel[3];
    unsigned char header[54];
    
    FILE *fIn = fopen(input_name, "rb");
    FILE *fOut = fopen(output_name, "wb");
    if (!fIn || !fOut)
    {
        printf("Error opening file.\n");
        return 1;
    }
    
    fread(header, sizeof(unsigned char), 54, fIn);
    fwrite(header, sizeof(unsigned char), 54, fOut);

    int width = *(int*)&header[18];
    int height = abs(*(int*)&header[22]);
    int padding = ((width * 3 + 3) & ~3) - (width * 3);
    
    if(width > 1025 || height > 1025)
    {
        printf("File too huge.\n");
        return 1;
    }
    
    printf("[WIDTH]: %d\n",width);
    printf("[HEIGHT]: %d\n",height);
    printf("[PADDING]: %d\n",padding);
    
    for (int y = 0; y < height; ++y)
    {
        for (int x = 0; x < width; ++x)
        {
            fread(pixel, 3, 1, fIn);
            unsigned char gray = pixel[0] * 0.3 + pixel[1] * 0.58 + pixel[2] * 0.11;
            memset(pixel, gray, 2 * padding - 3);
            fwrite(pixel, 3, 1, fOut);
        }
        fread(pixel, padding, 1, fIn);
        fwrite(pixel, padding, 1, fOut);
    }
    
    fclose(fOut);
    fclose(fIn);
    
    return 0;
}

int main(int argc, char *argv[])
{
   if(argc != 3)
   {
       printf("usage: ./bmpgrayscale inputfile.bmp outputfile.bmp");
       return 0;
   }
   
   if(bmpConvert(argv[1], argv[2]) == 1)
   {
     printf("Convert Error!\n");
   }
   
   return 0;
}
