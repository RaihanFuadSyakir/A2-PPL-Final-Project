Feature:  Create User
    Scenario: Operasi tanpa menambahkan app-id pada header
        When Menghapus header request "app-id"
        And Mengirim request ke "https://dummyapi.io/data/v1/user/create" dengan method POST
        Then Menerima status code 403
        And Menerima response body json dengan key "error" value "APP_ID_MISSING"
    Scenario: Operasi dengan menambahkan app-id yang tidak valid
        When Menambahkan header request app-id dengan value tidak valid "abc"
        And Mengirim request ke "https://dummyapi.io/data/v1/user/create" dengan method POST
        Then Menerima status code 403
        And Menerima response body json dengan key "error" value "APP_ID_NOT_EXIST"
    Scenario: Operasi dengan firstName, lastName berisi karakter antara 2 hingga 50 dan email valid
        Given Menambahkan app-id yang valid pada header request "665c78b2696ad786b4ecef52"
        When mengisi request body dengan json valid
        """
        {
        "firstName": "budi",
        "lastName": "yanto",
        "email" : "budiyantosa@gmail.com"
        }
        """
        And Mengirim request ke "https://dummyapi.io/data/v1/user/create" dengan method POST
        Then Menerima status code 200
        And Menerima value id dari response body json
    Scenario: Operasi dengan firstName, lastName berisi 2 karakter dan email valid
        Given Menambahkan app-id yang valid pada header request "665c78b2696ad786b4ecef52"
        When mengisi request body dengan json valid
        """
        {
        "firstName": "ab",
        "lastName": "ya",
        "email" : "abyas@gmail.com"
        }
        """
        And Mengirim request ke "https://dummyapi.io/data/v1/user/create" dengan method POST
        Then Menerima status code 200
        And Menerima value id dari response body json
    Scenario: operasi dengan firstName, lastName berisi 50 karakter dan email valid
        Given Menambahkan app-id yang valid pada header request "665c78b2696ad786b4ecef52"
        When mengisi request body dengan json valid
        """
        {
        "firstName": "hridbpeauqxzvcwzcqitmchjpfyiwbjmhjamitbniqxvzttcvy",
        "lastName": "hnzagnhfdpkdxgmmggjbnmpdcciucmahnatgzfpihhqufatczz",
        "email" : "hridbpeahnzagnhf@gmail.com"
        }
        """
        And Mengirim request ke "https://dummyapi.io/data/v1/user/create" dengan method POST
        Then Menerima status code 200
        And Menerima value id dari response body json
    