package com.ephemeralin.utils;

import lombok.extern.log4j.Log4j2;
import org.apache.commons.io.IOUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

/**
 * The type File utility.
 */
@Log4j2
public class FileUtility {

    /**
     * Get file from multipart byte [ ].
     *
     * @param file the file
     * @return the byte [ ]
     */
    public static byte[] getFileFromMultipart(MultipartFile file) {
        byte[] image = null;
        InputStream fileContent = null;
        try {
            fileContent = file.getInputStream();
            image = IOUtils.toByteArray(fileContent);
        } catch (IOException e) {
            log.error(e.getStackTrace());
        }
        return image;
    }
}
