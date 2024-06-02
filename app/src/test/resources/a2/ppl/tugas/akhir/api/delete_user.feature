Feature: Delete User
    Scenario: Pengujian delete data user tanpa menambahkan app-id pada header request
        When kirim request DELETE ke endpoint "https://dummyapi.io/data/v1/user/60d0fe4f5311236168a109fa"
        Then Menerima Status Response : 403 "Forbidden"
        And Meneriman Respond Body : "APP_ID_MISSING"
    Scenario: Pengujian delete data user dengan menggunakan app-id yang tidak terdaftar pada header request
        Given Menambahkan app-id yang tidak terdaftar pada header request "6633dd597309359fe9ceb8aa" 
        When kirim request DELETE ke endpoint "https://dummyapi.io/data/v1/user/60d0fe4f5311236168a109fa"
        Then Menerima Status Response : 403 "Forbidden"
        And Meneriman Respond Body : "APP_ID_NOT_EXIST"
    Scenario: Pengujian delete data user dengan menggunakan app-id yang terdaftar pada header request dan format id yang terdaftar
        Given Menambahkan app-id yang terdaftar pada header request "6633dd597309359fe9ceb8ad"
        When kirim request DELETE ke endpoint "https://dummyapi.io/data/v1/user/60d0fe4f5311236168a109fa"
        Then Menerima Status Response : 200 "OK"
        And Meneriman Respond Body : ID
    Scenario: Pengujian delete user dengan menggunakan app-id yang terdaftar pada header request dan format id yang terdaftar tetapi telah dihapus
        Given Menambahkan app-id yang terdaftar pada header request "6633dd597309359fe9ceb8ad"
        When kirim request DELETE ke endpoint "https://dummyapi.io/data/v1/user/60d0fe4f5311236168a109fa"
        And Meneriman Respond Body : "RESOURCE_NOT_FOUND"
    Scenario: Pengujian get user dengan menggunakan app-id yang terdaftar pada header request dan format id tidak terdaftar
        Given Menambahkan app-id yang terdaftar pada header request "6633dd597309359fe9ceb8ad"
        When kirim request DELETE ke endpoint "https://dummyapi.io/data/v1/user/60d0fe4f5311236168a109fa"
        Then Menerima Status Response : 400 "Bad Request"
        And Meneriman Respond Body : "PARAMS_NOT_VALID"