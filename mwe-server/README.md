# mwe-server


## Swagger

[http://localhost:8087/swagger-ui.html](http://localhost:8087/swagger-ui.html)


## Requests zum Rumspielen

### Carts und Items

* GET für den Cart von `thomas.maier` (hat id 1 über DataInitializer)
  * [http://localhost:8087/api/carts/1](http://localhost:8087/api/carts/1)
  * Bsp Response Body:
    ```json
    {
        "name": "Amazon Warenkorb",
        "username": "thomas.maier",
        "items": [
            {
                "id": 2,
                "productId": 4,
                "productName": "Handy",
                "productText": "Text zu Handy",
                "productPrice": 999.99,
                "quantity": 1
            },
            {
                "id": 3,
                "productId": 3,
                "productName": "Stift",
                "productText": "Text zu Stift",
                "productPrice": 2.99,
                "quantity": 1
            },
            {
                "id": 1,
                "productId": 1,
                "productName": "Tastatur",
                "productText": "Text zu Tastatur",
                "productPrice": 57.99,
                "quantity": 1
            }
        ],
        "priceSum": 1060.97
    }
    ```

* PUT um den Cart von `thomas.maier` (id 1) zu aktualisieren
  * [http://localhost:8087/api/carts/thomas.maier](http://localhost:8087/api/carts/thomas.maier)
  * Bsp Request Body:
    ```json
    {
        "name": "Edeka Warenkorb",
        "username": "thomas.maier"
    }
    ```
* DELETE um ein Item vom `thomas.maier` Cart zu entfernen
  * [http://localhost:8087/api/carts/1/items/2](http://localhost:8087/api/carts/1/items/2)
  * Bsp Response Body:
    ```json
    {
        "name": "Edeka Warenkorb",
        "username": "thomas.maier",
        "items": [
            {
                "id": 3,
                "productId": 3,
                "productName": "Stift",
                "productText": "Text zu Stift",
                "productPrice": 2.99,
                "quantity": 1
            },
            {
                "id": 1,
                "productId": 1,
                "productName": "Tastatur",
                "productText": "Text zu Tastatur",
                "productPrice": 57.99,
                "quantity": 1
            }
        ],
        "priceSum": 60.98
    }
    ```

* POST um zwei Fernseher (product id 2) zum Cart von `thomas.maier` hinzuzufügen
  * Bsp Request Body:
    ```json
    {
        "productId": 2,
        "quantity": 2
    }
    ```
  * Bsp Response Body:
    ```json
    {
        "name": "Edeka Warenkorb",
        "username": "thomas.maier",
        "items": [
            {
                "id": 6,
                "productId": 2,
                "productName": "Fernseher",
                "productText": "Text zu Fernseher",
                "productPrice": 488.99,
                "quantity": 2
            },
            {
                "id": 3,
                "productId": 3,
                "productName": "Stift",
                "productText": "Text zu Stift",
                "productPrice": 2.99,
                "quantity": 1
            },
            {
                "id": 1,
                "productId": 1,
                "productName": "Tastatur",
                "productText": "Text zu Tastatur",
                "productPrice": 57.99,
                "quantity": 1
            }
        ],
        "priceSum": 1038.96
    }
    ```

### Products

* GET um alle Produkte nach Name sortiert zu bekommen
  * [http://localhost:8087/api/products](http://localhost:8087/api/products)

* POST um ein Product neu anzulegen (mit der ID die dann zurück kommt kann man das Produkt dann in Carts hinzufügen)
  * [http://localhost:8087/api/products](http://localhost:8087/api/products)
  * Bsp Request Body:
    ```json
    {
        "name": "Schreibtisch",
        "text": "Text zu Schreibtisch",
        "price": 166.99
    }
    ```
  * Bsp Response Body:
    ```json
    {
        "id": 5,
        "name": "Schreibtisch",
        "text": "Text zu Schreibtisch",
        "price": 166.99
    }
    ```


## Weiterführendes 1

* ModelMapper oder Mapstruct
* CreatedBy, CreatedDate, LastModifiedBy, LastModifiedDate in der DB für Carts, und Products
* Eigene JSON Struktur für HTTP Response Exceptions (Stichtwort Rest Exception Handling)


## Weiterführendes 2

* Wenn du Microservices machen willst, dann machst einen eigenen Service der sich nur um Produkte kümmert
* Hier ist dann wichtig z.b. Fallbacks einzubauen, wenn der ProductService mal nicht da ist
* etc.
