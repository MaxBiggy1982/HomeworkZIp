package mtotski;



import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.List;
import java.util.Objects;
import java.util.zip.*;



public class FileTest {


    ClassLoader cl = FileTest.class.getClassLoader();
    String zipFile = "testzip.zip";


    @DisplayName("ZipCsv test")
    @Test
    void zipCsvTest() throws Exception {
        try (ZipFile zf = new ZipFile(new File("src/test/resources/" + zipFile))) {
            ZipInputStream is = new ZipInputStream(Objects.requireNonNull(cl.getResourceAsStream(zipFile)));
            ZipEntry entry;
            while ((entry = is.getNextEntry()) != null) {
                if (entry.getName().contains(".csv")) {
                    try (InputStream inputStream = zf.getInputStream(entry)) {
                        CSVReader reader = new CSVReader(new InputStreamReader(inputStream));
                        List<String[]> content = reader.readAll();
                        String[] row = content.get(1);
                        assertThat(row[0]).isEqualTo("Snack");
                        assertThat(row[1]).isEqualTo("5");
                    }
                }
            }
        }
    }

    @DisplayName("ZipPdf test")
    @Test
    void zipPdfTest() throws Exception {
        try (ZipFile zf = new ZipFile(new File("src/test/resources/" + zipFile))) {
            ZipInputStream is = new ZipInputStream(Objects.requireNonNull(cl.getResourceAsStream(zipFile)));
            ZipEntry entry;
            while ((entry = is.getNextEntry()) != null) {
                if (entry.getName().contains(".pdf")) {
                    try (InputStream inputStream = zf.getInputStream(entry)) {
                        PDF pdf = new PDF(inputStream);
                        assertThat(pdf, containsText("appropriate"));
                    }
                }
            }
        }
    }
        @DisplayName("ZipXlsx test")
        @Test
        void zipXlsxTest () throws Exception {
            try (ZipFile zf = new ZipFile(new File("src/test/resources/" + zipFile))) {
                ZipInputStream is = new ZipInputStream(Objects.requireNonNull(cl.getResourceAsStream(zipFile)));
                ZipEntry entry;
                while ((entry = is.getNextEntry()) != null) {
                    if (entry.getName().contains(".xlsx")) {
                        try (InputStream inputStream = zf.getInputStream(entry)) {
                            XLS xls = new XLS(inputStream);
                            assertThat(
                                    xls.excel.getSheetAt(0)
                                            .getRow(2)
                                            .getCell(2)
                                            .getStringCellValue()
                            ).isEqualTo("Ksyusha");
                        }
                    }
                }
            }
        }


    }

