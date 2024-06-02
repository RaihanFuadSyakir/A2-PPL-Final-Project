Feature: Get User
    Scenario: Pengujian get data user tanpa menambahkan app-id pada header request
        When Kirim req GET ke endpoint "https://dummyapi.io/data/v1/user/60d0fe4f5311236168a109ff"
        Then Menerima Status Code : 403 "Forbidden"
        And Meneriman Respond Body : "APP_ID_MISSING"
    Scenario: Pengujian get data user dengan menggunakan app-id yang tidak terdaftar pada header request
        Given Menambahkan app-id yang tidak terdaftar pada header request "6633dd597309359fe9ceb8aa"
        When Kirim req GET ke endpoint "https://dummyapi.io/data/v1/user/60d0fe4f5311236168a109ff"
        Then Menerima Status Code : 403 "Forbidden"
        And Meneriman Respond Body : "APP_ID_NOT_EXIST"
    Scenario: Pengujian get data user dengan menggunakan app-id yang terdaftar pada header request dan format id yang terdaftar
        Given Menambahkan app-id yang terdaftar pada header request "6633dd597309359fe9ceb8ad"
        When Kirim req GET ke endpoint "https://dummyapi.io/data/v1/user/60d0fe4f5311236168a109ff"
        Then Menerima Status Code : 200 "OK"
        And Meneriman Respond Body : Respond JSON sesuai dengan ID "60d0fe4f5311236168a109ff"
    Scenario: Pengujian get user dengan menggunakan app-id yang terdaftar pada header request dan format id yang tidak terdaftar
        Given Menambahkan app-id yang terdaftar pada header request "6633dd597309359fe9ceb8ad"
        When Kirim req GET ke endpoint "https://dummyapi.io/data/v1/user/60d0fe4f5311236168a109f1"
        Then Menerima Status Code : 404 "Not Found"
        And Meneriman Respond Body : "RESOURCE_NOT_FOUND"
    Scenario: Pengujian get user dengan menggunakan app-id yang terdaftar pada header request dan parameter  tidak sesuai format id
        Given Menambahkan app-id yang terdaftar pada header request "6633dd597309359fe9ceb8ad"
        When Kirim req GET ke endpoint "https://dummyapi.io/data/v1/user/60d0fe4f5311236168a109fa5"
        Then Menerima Status Code : 400 "Bad Request"
        And Meneriman Respond Body : "PARAMS_NOT_VALID"

