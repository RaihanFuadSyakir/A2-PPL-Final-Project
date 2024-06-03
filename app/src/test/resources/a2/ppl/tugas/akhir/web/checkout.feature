Feature : Checkout
    Scenario : Tes Tampilan Halaman Checkout Information
        Given pengguna sudah login
        And ada barang di dalam keranjang
        When pengguna menekan tombol Cart
        And pengguna menekan tombol Checkout pada halaman Cart
        Then halaman Informasi Checkout ditampilkan
    Scenario : Tes Tampilan Halaman Shipping Information setelah memasukkan informasi yang valid
        Given halaman Checkout Information sudah tampil
        When pengguna memasukkan informasi pada field First name, Last name, dan Zip/Postal Code dengan valid
        And pengguna menekan tombol Continue
        Then halaman Shipping Information ditampilkan
    Skenario: Menampilkan Halaman Cart setelah menekan tombol "Cancel"
        Given halaman Checkout Information sudah tampil
        When pengguna menekan tombol Cancel
        Then halaman Cart ditampilkan