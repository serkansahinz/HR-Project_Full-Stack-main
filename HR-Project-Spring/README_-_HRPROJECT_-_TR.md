# HR Project

## İnsan Kaynakları Yönetim Sistemi

Bir platform üzerinden ticari şirketlerin insan kaynakları süreçlerini yönetmek amacıyla oluşturulan bir proje.
Projenin tanıtım videosuna aşağıdki linkten ulaşabilirsiniz.

[YouTube Video](https://www.youtube.com/watch?v=tM-Fa5lB-zc&ab_channel=%C3%9CnalGaniBerk)

### Architecture

<img src="https://github.com/onurbass/HR-Project_Full-Stack/blob/main/HR-Project-Spring/src/main/resources/images/cloud-architecture.jpg?rav=true" alt="Icon" >

### Proje Hakkında

Bu insan kaynakları uygulaması, dört farklı rol içermektedir: admin, şirket yöneticisi, çalışan ve misafir. Her rol, kendi görevlerine uygun olarak tasarlanmış bir kontrol paneli, profil ve sayfalarla donatılmıştır.

Bir şirket yöneticisi kayıt olduğunda, kayıtları admin tarafından onaylanmaktadır. Onaylandıktan sonra, şirket yöneticisi sisteme erişim kazanır ve ardından çalışanları sisteme ekleyebilir.

Çalışan profil sayfası, kişisel bilgileri, maaş detayları ve vardiya programlarını içermektedir. Çalışan kontrol paneli ayrıca genel tatil bilgilerini ve istihdam edildikleri şirketle ilgili detayları sunmaktadır.

Çalışanlar, şirketleriyle ilgili yorumlarını iletebilir, ancak bu yorumlar yalnızca admin onayından sonra yayınlanır.

Şirket yöneticileri için, şirketlerinin mali durumuyla ilgili kapsamlı bilgiler içeren özel bir sayfa bulunmaktadır; bu bilgiler arasında gelir, gider ve kar/zarar verileri yer almaktadır.

Uygulamaya kayıt olan misafirler, uygulama içinde kayıtlı olan tüm şirketlere ait bilgilere ve yorumlara erişebilirler.

### Kullanım

Bu örnek uygulamaları çalıştırmak için Gradle ve JDK 8 veya daha yüksek sürümlere sahip olmanız gerekmektedir. Ayrıca, MongoDB, PostgreSQL ve RabbitMQ'yu Docker konteynerinde çalıştırmanız gerekecek, bu nedenle yerel makinenizde Docker yüklü olmalıdır.

### Docker Compose Kullanımı

1. Proje içerisinde yer alan Docker compose dosyasını çalıştırarak gerekli bağımlılıkları dockera kurabilir ve çalıştırabilirsiniz.
