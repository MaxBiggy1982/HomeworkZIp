package mtotski;

import mtotski.jsonclass.Product;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import com.codeborne.pdftest.PDF;  // ne rabotaet add library to class path ne dobavlyaet.
import com.codeborne.xlstest.XLS;  // ne rabotaet add library to class path ne dobavlyaet.
import com.opencsv.CSVReader;  // ne rabotaet add library to class path ne dobavlyaet.
import static org.assertj.core.api.Assertions.assertThat; // ne rabotaet add library to class path ne dobavlyaet.
import java.io.File;



public class JacksonTest {

    String jsonFile = "jsonformatter.json";

    @DisplayName("Json check value")
    @Test
    void jsonCheckValueTest() throws Exception {
        File file = new File("src/test/resources/"+ jsonFile);
        ObjectMapper objectMapper = new ObjectMapper();
        Product product = objectMapper.readValue(file, Product.class);
        assertThat(product.id).isEqualTo(1);
        assertThat(product.category).isEqualTo("Cat Food");
        assertThat(product.name).isEqualTo("Chicken Can");
        assertThat(product.available).isEqualTo(true);
        assertThat(product.tags[0]).isEqualTo("chicken");
        assertThat(product.characteristics.get("PriceEUR")).isEqualTo(55);
    }




}
