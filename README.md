# :lollipop: DBD Infos

Um aplicativo feito em inglês para visualizar as habilidades de "killers" e "sobreviventes" do jogo Dead by Daylight. O aplicativo serve para ter ideias de "Builds" antes de abrir o jogo. Habilidades com descrição.

## :rescue_worker_helmet: Instruções de uso

Para ter acesso ao aplicativo é necessário criar uma conta. Acesse a tela de Cadastro "Sign up", e digite um email e senha válidos para poder criar sua conta. Esses dados serão processados pelo Firebase que automaticamente criarão uma conta, caso o usuário insira email e senha válidos. Em seguida, o usuário está pronto para usar a interface do aplicativo

### Tecnologias utilizadas
1. [Coroutines](https://github.com/Kotlin/kotlinx.coroutines)
1. [Clean Architecture](https://pusher.com/tutorials/clean-architecture-introduction)
1. [Koin](https://insert-koin.io)
1. [MVVM Architecture](https://developer.android.com/topic/libraries/architecture/viewmodel)
1. [Picasso](https://square.github.io/picasso/)
1. [Android Material Design](https://developer.android.com/guide/topics/ui/look-and-feel?hl=pt)
1. [Retrofit](https://square.github.io/retrofit/)
1. [Firebase Authentication](https://firebase.google.com/docs/auth)

### Soluções:
* Lógica importante para reduzir o tempo do usuário em acessar o conteúdo e sempre estar logado.
* Splashscreen com abertura diferenciada e com pouco custo de processamento
* Design seguindo os padrões de Material Design da Google
* Validação de informação do usuário e prontamente retorno de erro
* Arquitetura MVVM(Model-View-ViewModel) aplicada ao projeto incluindo a utilização de SharedViewModel
* Injeção de dependências utilizando o Koin
* Coroutines prontas em todo o aplicativo para que as chamadas de API sejam de forma assíncrona e segura para o usuário

### Próximos passos:
* Substituição de API pela minha própria (criação em andamento)
* Implementação de Testes Unitários com tecnologia a ser decidida
* NavigationDrawer para acesso a imagem de outras habilidades do jogo
* Subir o aplicativo no Google Play Store (após troca de API)

### :camera: Imagens
![Mainr](https://i.imgur.com/ZZ3Ju4Z.jpg) ![Main2r](https://i.imgur.com/ziWWqOm.jpg) ![Main3r](https://i.imgur.com/5XzOQt6.jpg) ![Main4r](https://i.imgur.com/LiffrIl.jpg)
