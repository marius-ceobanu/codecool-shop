<!--
*** Thanks for checking out the Best-README-Template. If you have a suggestion
*** that would make this better, please fork the repo and create a pull request
*** or simply open an issue with the tag "enhancement".
*** Thanks again! Now go create something AMAZING! :D
-->



<!-- PROJECT SHIELDS -->
<!--
*** I'm using markdown "reference style" links for readability.
*** Reference links are enclosed in brackets [ ] instead of parentheses ( ).
*** See the bottom of this document for the declaration of the reference variables
*** for contributors-url, forks-url, etc. This is an optional, concise syntax you may use.
*** https://www.markdownguide.org/basic-syntax/#reference-style-links
-->
[![Contributors][contributors-shield]][contributors-url]
[![Forks][forks-shield]][forks-url]
[![Stargazers][stars-shield]][stars-url]
[![Issues][issues-shield]][issues-url]
[![LinkedIn - Marius][linkedin-shield]][linkedin-marius-url]
[![Github - Marius][github-marius-shield]][github-marius-url]
[![Github - Razvan][github-razvan-shield]][github-razvan-url]



<!-- PROJECT LOGO -->
<br />
<p align="center">
  <a href="https://github.com/marius-ceobanu/codecool-shop.git">
    <img src="src/main/webapp/static/img/codecool-logo.png" alt="Logo" width="300">
  </a>

<h3 align="center">CodeCool Shop</h3>

  <p align="center">
    An online eCommerce web-application!
    <br />
    <a href="https://github.com/marius-ceobanu/codecool-shop.git"><strong>Explore the docs »</strong></a>
    <br />
    <br />
    <a href="https://github.com/marius-ceobanu/codecool-shop">View Demo</a>
    ·
    <a href="https://github.com/marius-ceobanu/codecool-shop/issues">Report Bug</a>
    ·
    <a href="https://github.com/marius-ceobanu/codecool-shop/issues">Request Feature</a>
  </p>



<!-- TABLE OF CONTENTS -->
<details open="open">
  <summary>Table of Contents</summary>
  <ol>
    <li>
      <a href="#about-the-project">About The Project</a>
      <ul>
        <li><a href="#built-with">Built With</a></li>
      </ul>
    </li>
    <li>
      <a href="#getting-started">Getting Started</a>
      <ul>
        <li><a href="#prerequisites">Prerequisites</a></li>
        <li><a href="#installation">Installation</a></li>
      </ul>
    </li>
    <li><a href="#usage">Usage</a></li>
    <li><a href="#roadmap">Roadmap</a></li>
    <li><a href="#contributing">Contributing</a></li>
    <li><a href="#contact">Contact</a></li>
    <li><a href="#acknowledgements">Acknowledgements</a></li>
  </ol>
</details>



<!-- ABOUT THE PROJECT -->
## About The Project

It's time to enhance the Online Shop, an online eCommerce web-application with Java.  

Here's why:
* Visitors can check the list of products and filter through (type and brand).
* Visitors can register for an account or login if they have one already.
* Users can not only browse products, add them into a Shopping Cart (edit), checkout items and make payments (paypal & card).
* They can log in and see the abandoned shopping cart or order history.
* Users can add / update their user details.
* Admin can access files with order details (JSON).
* User receive an email confirmation for their account and order.

### Built With

#### Backend
* [Java](https://www.java.com/)
* [Java Servlets](https://docs.oracle.com/javaee/5/tutorial/doc/bnafe.html/)
* [JDBC](https://docs.oracle.com/javase/tutorial/jdbc/basics/index.html)
* [PostgreSQL DataBase](https://www.postgresql.org/)

#### Frontend
* [Thymeleaf](https://www.thymeleaf.org/)

#### Version control
* [Github](https://www.github.com/)

#### Dependency manager and build tool
* [Maven](https://maven.apache.org/)

#### Project Management
* [Trello](https://www.trello.com/)



<!-- GETTING STARTED -->
## Getting Started

This application can be tested by installing all prerequisites, clone the repo, build the project from pom.xml, configure a jetty server in your IDE and enjoy!

### Prerequisites

All prerequisites must be installed, accordingly to the technologies used in this project:

### Installation

* Backend

1. Clone the repo
   ```sh
   git clone https://github.com/marius-ceobanu/codecool-shop.git
   ```
2. Build the pom.xml in order to download dependencies.

4.Configure a Maven jetty:run server.

3. Run the app, and go on your browser on localhost:8080.

<!-- USAGE EXAMPLES -->
## Usage

Further we will shortly name, describe and visualize some main features of the app.

### Welcome page
* On the home page, the guest can see a carousel and listed all the products available in the shop (image / description / supplier / price).
* The products can be sorted by type or brand.

[![home-gif][home-gif]]()

* The guest has the option to register and create a user account in order to be able to order. A confirmation email will be sent.

[![registration-png][registration-png]]()
[![account-confirmation-png][account-confirmation-png]]()

* If the guest already has an active account, he can login and further administrate / update his user details.

[![login-gif][login-gif]]()

### Order
* Users can add products to their carts while on the cart icon updates.

[![buy-png][buy-png]]()

* Users can update cart (adding / removing items).

[![cart-png][cart-png]]()

* User can proceed to checkout by adding the order details and choose the payment (card / paypal).

[![checkout1-png][checkout1-png]]()
[![checkout2-png][checkout2-png]]()
[![checkout3-png][checkout3-png]]()
[![card-png][card-png]]()

* If the payment is processed successfully, the user gets a confirmation message and mail conformation.
* User can also see the order present in order history, in user details.

[![order-confirmation1-png][order-confirmation1-png]]()
[![order-confirmation2-png][order-confirmation2-png]]()
[![order-history-png][order-history-png]]()

<!-- ROADMAP -->
## Roadmap

The project development took place through 2 Agile iterations, each iteration taking 4 days, and presenting the results in the 5th. A short complete roadmap bellow:

[![agile][agile]]()

* Sprint 1: Main page / Filters / Cart / Checkout / Order History (JSON file) / Confirmation email. 
* Spring 2: Migrate to DB (PostgreSQL) / Implement JDBC / Registration email / Login / User details / Order history (DB) / Unit testing.

<!-- CONTRIBUTING -->
## Contributing

Contributions are what make the open source community such an amazing place to be learn, inspire, and create. Any contributions you make are **greatly appreciated**.

1. Fork the Project
2. Create your Feature Branch (`git checkout -b feature/AmazingFeature`)
3. Commit your Changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the Branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request


<!-- CONTACT -->
## Contact

Marius Ceobanu - [@My Github](https://github.com/marius-ceobanu) [@My LinkedIn](https://www.linkedin.com/in/marius-ciprian-ceobanu-3431157b) - ceobanu.marius@gmail.com

Razvan Grigore - [@My Github](https://github.com/rgrigore) - razvang95@gmail.com


<!-- ACKNOWLEDGEMENTS -->
## Acknowledgements
* [Java Dao pattern](https://www.baeldung.com/java-dao-pattern)
* [Introducing servlets](project/curriculum/materials/pages/java/introducing-servlets.md)
* [Servlet tutorial](https://www.tutorialspoint.com/servlets/servlets-form-data.htm)
* [Java properties](https://www.baeldung.com/java-properties)
* [Codecool Romania :thumbsup:](https://codecool.com/ro/)
* [Img Shields](https://shields.io)


<!-- MARKDOWN LINKS & IMAGES -->
<!-- https://www.markdownguide.org/basic-syntax/#reference-style-links -->
[contributors-shield]: https://img.shields.io/badge/Contributers-2-brightgreen
[contributors-url]: https://github.com/marius-ceobanu/codecool-shop/graphs/contributors
[forks-shield]: https://img.shields.io/badge/Forks-0-blue
[forks-url]: https://github.com/marius-ceobanu/codecool-shop/network/members
[stars-shield]: https://img.shields.io/badge/Stars-2-blue
[stars-url]: https://github.com/marius-ceobanu/codecool-shop/stargazers
[issues-shield]: https://img.shields.io/github/issues/marius-ceobanu/codecool-shop
[issues-url]: https://github.com/marius-ceobanu/codecool-shop/issues
[linkedin-shield]: https://img.shields.io/twitter/url?label=Linkedin%20-%20Marius&logo=LINKEDIN&style=social&url=https%3A%2F%2Fwww.linkedin.com%2Fin%2Fmarius-ciprian-ceobanu-3431157b
[linkedin-marius-url]: https://www.linkedin.com/in/marius-ciprian-ceobanu-3431157b
[github-marius-shield]: https://img.shields.io/twitter/url?label=GitHub%20-%20Marius&logo=Github&style=social&url=https%3A%2F%2Fgithub.com%2Fmarius-ceobanu
[github-marius-url]: https://github.com/marius-ceobanu
[github-razvan-shield]: https://img.shields.io/twitter/url?label=GitHub%20-%20Razvan&logo=Github&style=social&url=https%3A%2F%2Fgithub.com%2Frgrigore
[github-razvan-url]: https://github.com/rgrigore
<!-- IMAGES -->
[home-gif]: src/main/resources/home.gif
[registration-png]: src/main/resources/register.png
[account-confirmation-png]: src/main/resources/account_confirmation.png
[login-gif]: src/main/resources/login.gif
[buy-png]: src/main/resources/adding-to-cart.png
[cart-png]: src/main/resources/cart.png
[checkout1-png]: src/main/resources/checkout1.png
[checkout2-png]: src/main/resources/checkout2.png
[checkout3-png]: src/main/resources/checkout3.png
[card-png]: src/main/resources/card_payment.png
[order-confirmation1-png]: src/main/resources/order-confirmation1.png
[order-confirmation2-png]: src/main/resources/order_confirmation2.png
[order-history-png]: src/main/resources/order_history.png
[agile]: src/main/resources/agile-logo.png
