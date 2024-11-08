import java.text.DecimalFormat;
import javax.swing.JOptionPane;

public class PerhitunganDiskonHelper {
    protected static final String VALID_COUPON_CODE = "DISCOUNT10";
    protected static final double COUPON_DISCOUNT = 10;
    private static StringBuilder history = new StringBuilder(); 

    public static double calculateFinalPrice(double originalPrice, double discountPercentage, String couponCode) {
        double discountAmount = originalPrice * (discountPercentage / 100);
        double priceAfterDiscount = originalPrice - discountAmount;

        if (couponCode.equalsIgnoreCase(VALID_COUPON_CODE)) {
            double couponDiscountAmount = priceAfterDiscount * (COUPON_DISCOUNT / 100);
            priceAfterDiscount -= couponDiscountAmount;
        }
        
        return priceAfterDiscount;
    }

    public static double calculateSavings(double originalPrice, double discountPercentage, String couponCode) {
        double discountAmount = originalPrice * (discountPercentage / 100);
        double totalSavings = discountAmount;

        if (couponCode.equalsIgnoreCase(VALID_COUPON_CODE)) {
            double couponDiscountAmount = (originalPrice - discountAmount) * (COUPON_DISCOUNT / 100);
            totalSavings += couponDiscountAmount;
        }

        return totalSavings;
    }

    public static double parsePrice(String priceText) {
        try {
            return Double.parseDouble(priceText);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Input harga tidak valid. Masukkan harga yang benar.", "Error", JOptionPane.ERROR_MESSAGE);
            return -1; 
        }
    }

    public static String formatPrice(double price) {
        DecimalFormat df = new DecimalFormat("#,###"); 
        return df.format(price);
    }
    
    public static void addHistory(double originalPrice, int discountPercentage, double finalPrice, double savings, String couponCode) {
        String riwayat = "Harga Asli: Rp " + formatPrice(originalPrice) + "\n" +
                         "Diskon: " + discountPercentage + "%" + "\n" +
                         "Kupon: " + (couponCode.equalsIgnoreCase(VALID_COUPON_CODE) ? "10%" : "Tidak ada") + "\n" +
                         "Harga Akhir: Rp " + formatPrice(finalPrice) + "\n" +
                         "Penghematan: Rp " + formatPrice(savings) + "\n" + "\n"; 

        history.append(riwayat); 
    }

    public static String getHistory() {
        return history.toString();
    }
}
