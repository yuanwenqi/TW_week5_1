package practice2;

import practice3.Order;

import java.math.BigDecimal;
import java.util.List;

public class Receipt {

    public Receipt() {
        tax = new BigDecimal(0.1);
        tax = tax.setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    private BigDecimal tax;

    public double CalculateGrandTotal(List<Product> products, List<OrderItem> items) {
        BigDecimal subTotal = GetsubTotal(products,items);
        BigDecimal taxTotal = subTotal.multiply(tax);
        BigDecimal grandTotal = subTotal.add(taxTotal);

        return grandTotal.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
    }




    private OrderItem findOrderItemByProduct(List<OrderItem> items, Product product) {
        OrderItem curItem = null;
        for (OrderItem item : items) {
            if (item.getCode() == product.getCode()) {
                curItem = item;
                break;
            }
        }
        return curItem;
    }


    private BigDecimal calculateSubtotal(List<Product> products, List<OrderItem> items) {
        BigDecimal subTotal = new BigDecimal(0);
        for (Product product : products) {
            BigDecimal itemTotal = GetItemTotal(product,items);
            subTotal = subTotal.add(itemTotal);
        }
        return subTotal;
    }


    private BigDecimal GetsubTotal(List<Product> products, List<OrderItem> items){
        BigDecimal subTotal = calculateSubtotal(products, items);
        for (Product product : products) {

            OrderItem curItem = findOrderItemByProduct(items, product);

            BigDecimal reducedPrice = product.getPrice()
                    .multiply(product.getDiscountRate())
                    .multiply(new BigDecimal(curItem.getCount()));
            subTotal = subTotal.subtract(reducedPrice);
        }
        return  subTotal;
    }

    private BigDecimal GetItemTotal(Product product,List<OrderItem> items){
        OrderItem item = findOrderItemByProduct(items, product);
        BigDecimal itemTotal = product.getPrice().multiply(new BigDecimal(item.getCount()));
        return itemTotal;
    }
}
