Feature: Cart

  Scenario: Berhasil melihat daftar barang pada halaman Cart ketika Cart kosong
    Given Pengguna berhasil melakukan login ke dalam aplikasi
    And Pengguna berhasil mengakses halaman Dashboard aplikasi
    When Pengguna klik gambar Cart pada halaman Dashboard untuk berpindah ke halaman Cart
    Then Sistem menampilkan daftar Qty dan Description kosong
    And Sistem menampilkan button Continue Shopping
    And Sistem menampilkan button Checkout

  Scenario: Berhasil kembali ke halaman dashboard
    Given Pengguna berhasil melakukan login ke dalam aplikasi
    And Pengguna berhasil mengakses halaman Cart aplikasi
    When Pengguna klik button Continue Shopping
    Then Sistem berpindah ke halaman Dashboard
    And Sistem menampilkan daftar katalog produk

  Scenario: Tidak berhasil melakukan checkout jika tidak terdapat barang pada halaman Cart
    Given Pengguna berhasil melakukan login ke dalam aplikasi
    And Pengguna berhasil mengakses halaman Cart aplikasi
    When Pengguna klik button Checkout
    Then Sistem menampilkan pesan error "You Need Item In Cart To Proceed Checkout Process" pada halaman Cart