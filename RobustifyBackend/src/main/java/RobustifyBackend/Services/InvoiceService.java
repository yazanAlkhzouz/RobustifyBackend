package RobustifyBackend.Services;

import RobustifyBackend.Controllers.DTOs.InvoiceDTO;
import RobustifyBackend.Controllers.DTOs.UpdateOrderStatus;
import RobustifyBackend.Repositories.ComplaintsRepository;
import RobustifyBackend.Repositories.InvoiceRepository;
import RobustifyBackend.Repositories.OrderRepository;
import RobustifyBackend.model.Invoices.Invoices;
import RobustifyBackend.model.Order.Order;
import RobustifyBackend.model.User.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
public class InvoiceService {
    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private OrderRepository orderRepository;
    @Transactional
    public Invoices createInvoice(InvoiceDTO invoiceDTO) throws Exception {
        Order order = orderRepository.findById(invoiceDTO.getOrderId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid order ID"));

        Invoices invoice = new Invoices();
        invoice.setOrder(order);
        double price = invoiceDTO.getUnitPrice();
        double discount = invoiceDTO.getDiscount();
        invoice.setDiscount(discount);
        double total_price = (price * order.getQuantity()) * (1 - (discount/100));
        invoice.setTotal_price(total_price);
        double VAT_RATE = 0.16;
        double grand_price = total_price * (1 + VAT_RATE);
        invoice.setGrand_total(grand_price);
        int invoice_number = (int)(Math.random() * 900000) + 100000;
        invoice.setInvoice_number(invoice_number);
        invoice.setInvoice_date(LocalDate.now());
        return invoiceRepository.save(invoice);

    }

}
