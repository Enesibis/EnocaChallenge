🛠️ Proje Özellikleri

## 🚀 Kullanılan Teknolojiler

- **Java 21**
- **Spring Boot 3.4**
- **MySQL**
- **Spring Web**
- **Spring Data JPA**
- **Spring Data JDBC**
- **Lombok**

## 📦 Proje Yapısı

1. Entity Yapıları ve BaseEntity

    BaseEntity
    Tüm entity sınıflarının miras aldığı, aşağıdaki ortak alanları içeren bir sınıftır:
        id
        createdAt
        updatedAt

    Product
    Ürün bilgilerini barındırır.
    Alanlar:
        name: Ürün adı.
        price: Ürün fiyatı.
        stock: Ürün stok bilgisi.

    Customer
    Müşteri bilgilerini içerir.
    Alanlar:
        name: Müşteri adı.
        email: Müşteri e-posta adresi.

    Cart
    Müşterinin aktif sepetini temsil eder.
    Alanlar:
        Ürünler (products).
        Toplam fiyat (totalPrice).

    Order
    Müşterinin tamamladığı siparişleri temsil eder.
    Alanlar:
        Sipariş tarihindeki ürün fiyatları kaydedilir.

2. İlişkilendirme

    Bir Customer (Müşteri), bir Cart (Sepet) ve birden fazla Order (Sipariş) ile ilişkilendirilebilir.
    Bir Order (Sipariş) veya Cart (Sepet), birden fazla Product (Ürün) içerebilir.

3. Sepet ve Sipariş Yönetimi

    Sepete Ürün Ekleme
    Ürün sepete eklendiğinde:
        Toplam fiyat güncellenir.
        Ürün miktarı artırılır.

    Sepetten Ürün Çıkarma
    Ürün çıkarıldığında:
        Toplam fiyat yeniden hesaplanır.

    Miktar Değişikliği
    Ürün miktarı artırıldığında veya azaltıldığında toplam fiyat dinamik olarak güncellenir.

    Sipariş Geçme
    Müşteri sipariş verdiğinde:
        Sipariş anındaki ürün fiyatları ayrı bir tabloya kaydedilir.
        Sepet sıfırlanır.

4. Fiyat ve Stok Yönetimi

    Geçmiş Fiyat Görüntüleme
    Sipariş geçmişi, müşterinin ürünleri satın aldığı zamanki fiyatları saklar ve gösterir.

    Stok Takibi
        Ürün stoğu sıfır olduğunda:
            Ürün sepete eklenemez.
            Ürün için sipariş verilemez.

![enoca3](https://github.com/user-attachments/assets/c508f05a-73a9-4c33-828a-57a06240e345)
![enoca2](https://github.com/user-attachments/assets/444957af-1caa-4927-94c6-71887455180b)
![enoca1](https://github.com/user-attachments/assets/ca04e4b9-91ce-4fff-97b6-7e48c94abd0e)

