# Deezer player
Приложение для прослушивания сервиса Deezer

## Возможности
- Поиск и прослушивание треков через Deezer API
- Сохранение треков в кеш приложения для прослушивания оффлайн
- Управление проигрыванием при открытом приложении / из системного уведомления / часов

## Стек
- Kotlin
- Clean Architecture + MVVM
- Dagger2
- Jetpack Compose
- Retrofit + OkHttp
- Kotlin Coroutines
- Kotlinx Serialization
- Jetpack Navigation + BottomNavigation
- ExoPlayer
- MediaSession + Foreground Service

## Инструкция по запуску
1. Склонировать репозиторий
2. Открыть проект в Android Studio
3. ~~Подождать Gradle~~
4. Запустить проект

## Вопросы/проблемы

Экран скачанных треков - это экран сохранненых в кеш треков, я проовел соц опрос друзей и никто не ведет локальную библиотеку музыки, поэтому решил, что возможность сохранить трек офлайн и слушать его без интернета предпочтительнее

## Дальнейшие доработки
- Покрытие тестами
- При уходе с экрана плеера сделать отображение проигрывания для быстрого возврата
- Удаление треков из кеша