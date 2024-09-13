package com.example.picturecardsapp.ui.viewmodel


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.picturecardsapp.R
import com.example.picturecardsapp.data.model.WordModel

class WordsViewModel : ViewModel() {

    private val _randomWords = MutableLiveData<List<WordModel>>()
    val randomWords: LiveData<List<WordModel>> = _randomWords

    private val allWords = listOf(
        WordModel(1, "Computer", R.drawable.desktop, "I use a computer every day.", "Her gün bir bilgisayar kullanırım.", "Bilgisayar"),
        WordModel(2, "Apple", R.drawable.apple, "I like to eat an apple.", "Bir elma yemeyi severim.", "Elma"),
        WordModel(3, "Book", R.drawable.book, "I read a book before bed.", "Yatmadan önce bir kitap okurum.", "Kitap"),
        WordModel(4, "Phone", R.drawable.phone, "My phone is ringing.", "Telefonum çalıyor.", "Telefon"),
        WordModel(5, "Table", R.drawable.table, "The table is made of wood.", "Masa ahşaptan yapılmıştır.", "Masa"),
        WordModel(6, "Chair", R.drawable.dining, "This chair is comfortable.", "Bu sandalye rahat.", "Sandalye"),
        WordModel(7, "Lamp", R.drawable.lamp, "The lamp is on the desk.", "Lamba masanın üzerinde.", "Lamba"),
        WordModel(8, "Clock", R.drawable.clock, "The clock shows 3 PM.", "Saat 3'ü gösteriyor.", "Saat"),
        WordModel(9, "Cup", R.drawable.cup, "I drink coffee from this cup.", "Bu fincandan kahve içerim.", "Fincan"),
        WordModel(10, "Notebook", R.drawable.notebook, "I write notes in my notebook.", "Defterime notlar yazarım.", "Defter"),
        WordModel(11, "Sofa", R.drawable.sofa, "I sit on the sofa to watch TV.", "Televizyon izlemek için kanepeye otururum.", "Kanepe"),
        WordModel(12, "Window", R.drawable.window, "The window is open.", "Pencere açık.", "Pencere"),
        WordModel(13, "Pen", R.drawable.pencil, "I signed the document with a pen.", "Belgeyi bir kalemle imzaladım.", "Kalem"),
        WordModel(14, "Door", R.drawable.door, "Please close the door.", "Lütfen kapıyı kapat.", "Kapı"),
        WordModel(15, "Mirror", R.drawable.mirror, "The mirror is on the wall.", "Ayna duvarda asılı.", "Ayna"),
        WordModel(16, "Bed", R.drawable.bed, "I sleep in this bed.", "Bu yatakta uyurum.", "Yatak"),
        WordModel(17, "Fridge", R.drawable.fridge, "The fridge is full of food.", "Buzdolabı yiyecekle dolu.", "Buzdolabı"),
        WordModel(18, "Oven", R.drawable.oven, "I bake cakes in the oven.", "Fırında kek pişiririm.", "Fırın"),
        WordModel(19, "Washing Machine", R.drawable.washingmachine, "I use the washing machine to clean clothes.", "Çamaşırları yıkamak için çamaşır makinesini kullanırım.", "Çamaşır Makinesi"),
        WordModel(20, "Television", R.drawable.tv, "I watch movies on television.", "Televizyonda film izlerim.", "Televizyon"),
        WordModel(21, "Keyboard", R.drawable.keyboard, "I type on the keyboard.", "Klavye üzerinde yazarım.", "Klavye"),
        WordModel(22, "Mouse", R.drawable.mouse, "The mouse is wireless.", "Fare kablosuzdur.", "Fare"),
        WordModel(23, "Printer", R.drawable.printer, "I print documents with the printer.", "Belgeleri yazıcıyla yazdırırım.", "Yazıcı"),
        WordModel(24, "Camera", R.drawable.camera, "I take pictures with my camera.", "Kameramla fotoğraf çekerim.", "Kamera"),
        WordModel(25, "Banana", R.drawable.banana, "Bananas are yellow.", "Muzlar sarıdır.", "Muz"),
        WordModel(26, "Lemon", R.drawable.lemon, "Lemons taste sour.", "Limonlar ekşi tadındadır.", "Limon"),
        WordModel(27, "Air Conditioner", R.drawable.airconditioner, "The air conditioner keeps the room cool.", "Klima odayı serin tutar.", "Klima"),
        WordModel(28, "Fan", R.drawable.fan, "The fan is spinning fast.", "Vantilatör hızlı dönüyor.", "Vantilatör"),
        WordModel(29, "Water", R.drawable.water, "I drink a glass of water.", "Bir bardak su içerim.", "Su"),
        WordModel(30, "Cupboard", R.drawable.cupboard, "The cupboard is full of dishes.", "Dolap tabaklarla dolu.", "Dolap"),
        WordModel(31, "Drawer", R.drawable.drawer, "I keep my clothes in the drawer.", "Kıyafetlerimi çekmecede saklarım.", "Çekmece"),
        WordModel(32, "Shoe", R.drawable.shoes, "These shoes are new.", "Bu ayakkabılar yeni.", "Ayakkabı"),
        WordModel(33, "Hat", R.drawable.hat, "I wear a hat to protect from the sun.", "Güneşten korunmak için şapka takarım.", "Şapka"),
        WordModel(34, "Glasses", R.drawable.glasses, "I need glasses to see clearly.", "Net görebilmek için gözlüğe ihtiyacım var.", "Gözlük"),
        WordModel(35, "Watch", R.drawable.wristwatch, "My watch shows the time.", "Saatim zamanı gösterir.", "Saat"),
        WordModel(36, "Bag", R.drawable.bag, "I carry my books in the bag.", "Çantada kitaplarımı taşırım.", "Çanta"),
        WordModel(37, "Towel", R.drawable.towel, "I dry my hands with a towel.", "Ellerimi havluyla kurularım.", "Havlu"),
        WordModel(38, "Toothbrush", R.drawable.toothbrush, "I brush my teeth with a toothbrush.", "Dişlerimi diş fırçasıyla fırçalarım.", "Diş Fırçası"),
        WordModel(39, "Soap", R.drawable.soap, "I wash my hands with soap.", "Ellerimi sabunla yıkarım.", "Sabun"),
        WordModel(40, "Shampoo", R.drawable.shampoo, "I wash my hair with shampoo.", "Saçlarımı şampuanla yıkarım.", "Şampuan"),
        WordModel(41, "Brush", R.drawable.brush, "I brush my hair every day.", "Her gün saçlarımı tararım.", "Fırça"),
        WordModel(42, "Bicycle", R.drawable.bicycle, "I ride my bicycle to work.", "İşe bisikletle giderim.", "Bisiklet"),
        WordModel(43, "Ladder", R.drawable.ladder, "I use a ladder to reach high places.", "Yüksek yerlere ulaşmak için merdiven kullanırım.", "Merdiven"),
        WordModel(44, "Pillow", R.drawable.pillow, "I sleep on a soft pillow.", "Yumuşak bir yastıkta uyurum.", "Yastık"),
        WordModel(45, "Blanket", R.drawable.blanket, "I cover myself with a blanket at night.", "Gece battaniye ile örtünürüm.", "Battaniye"),
        WordModel(46, "Curtain", R.drawable.curtain, "The curtains block the sunlight.", "Perdeler güneş ışığını engeller.", "Perde"),
        WordModel(47, "Umbrella", R.drawable.umbrella, "I use an umbrella in the rain.", "Yağmurda şemsiye kullanırım.", "Şemsiye"),
        WordModel(48, "Vase", R.drawable.vase, "The vase is filled with flowers.", "Vazo çiçeklerle dolu.", "Vazo"),
        WordModel(49, "Plant", R.drawable.plant, "The plant needs water to grow.", "Bitkinin büyümek için suya ihtiyacı var.", "Bitki"),
        WordModel(50, "Napkin", R.drawable.napkin, "I wipe my mouth with a napkin.", "Ağzımı peçete ile silerim.", "Peçete")
    )


    init {
        loadWords()
    }

    private fun loadWords() {
        _randomWords.value = allWords.shuffled()
    }

    fun refreshWords() {
        _randomWords.value = _randomWords.value?.shuffled()
    }
}



