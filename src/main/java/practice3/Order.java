package practice3;

import java.math.BigDecimal;
import java.util.List;

public class Order extends PriceCaculator {

    private List<OrderLineItem> orderLineItemList;
    private List<BigDecimal> discounts;
    private BigDecimal tax;
    private BigDecimal subTotal;

    public Order(List<OrderLineItem> orderLineItemList, List<BigDecimal> discounts) {
        this.orderLineItemList = orderLineItemList;
        this.discounts = discounts;
        this.tax = new BigDecimal(0.1);
        this.subTotal = new BigDecimal(0);
    }

    public BigDecimal calculate() {

        // Total up line items
        Total_up_line_items();

        // Subtract discounts
        Subtract_discounts();

        // calculate tax
        BigDecimal tax = subTotal.multiply(this.tax);

        // calculate GrandTotal
        BigDecimal grandTotal = subTotal.add(tax);

        return grandTotal;
    }


    public void Total_up_line_items(){
        // Total up line items
        for (OrderLineItem lineItem : orderLineItemList) {
            subTotal = subTotal.add(lineItem.getPrice());
        }
    }
    public  void Subtract_discounts(){
        for (BigDecimal discount : discounts) {
            subTotal = subTotal.subtract(discount);
        }
    }



}
