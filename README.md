# 🪙 Crypto Catalogue

**Crypto Catalogue** -это pet-проект, созданный для отслеживания курсов избранных криптовалют с возможностью сохранения данных в локальной памяти.

---

## 🛠 Технический стек:
- UI: Jetpack Compose.
- Architecture: MVVM с четким разделением ответственности.
- Asynchrony & State: Kotlin Coroutines (`viewModelScope`, `lifecycleScope`), управление состоянием UI через `StateFlow`.
- Navigation: Jetpack Compose Navigation.
- Backend: Retrofit/Okhttp.
- Coins (local saving): Room, DAO.
- Settings (local saving): Datastore.
- Dependency Injection: Dagger Hilt.
- Testing: JUnit.

---

## 🚀 Возможности:
- Просмотр списка монет с отображением цены и изменением цены в процентах за 24 часа.
- Детальный просмотр отдельной монеты с отображением цены, объема, а также динамики изменения цены за 1 час, 24 часа и 7 дней.
- Ручная смена тема (светлая/темная).
- Ручная смена языка (системный/русский/английский).
- Возможность фильтра монет по названию и показ только выбранных монет.
- Локальное сохранение и показ данных о монетах/монете, а также настроек (тема/язык) в случае отсутствия сети.

---

## 📈Технические вызовы и полученный опыт:
- Работа с сетью: Для загрузки данных из сети использовал API CoinLore, который возвращает данные об одной монете в виде списка, а о нескольких монет в виде массива, внутри которого список.
 Из-за этого для API=запроса по возврату списка в коде пришлось создавать отдельный класс-прослойку. Научился внимательнее обращаться с возвращаемыми типами данных.
- Проектирование интерфейсов: освоил и применил на практике новые для себя инструменты UI (DropDownMenuBox, Switch, SingleChoiceSegmentedButtonRow).
- Оптимизация потоков :Впервые для себя применил фоновый поток lifecycleScope в MainActivity. Разобрался, как происходит вызов функций datastore для сохранения настроек при запуске приложения.
- Тестирование: применил на практике JUnit тесты для viewModel. Понял, что для тестирования viewModel нужно подменять поток на тестовый, а затем возвращать основной поток.
- Build gradle: Научился правильно внедрять зависимости для Dagger Hilt в проект. 

---

## 📸 Скриншоты приложения
<details>
<summary><b>Нажмите, чтобы посмотреть скриншоты интерфейса</b></summary>
  
### Просмотр списка и детальный показ отдельной монеты
![Image Alt](https://github.com/il427221-cyber/Crypto-Catalogue/blob/main/coins%20data.jpg?raw=true)

### Смена темы
![Image Alt](https://github.com/il427221-cyber/Crypto-Catalogue/blob/main/theme%20change.jpg?raw=true)

### Смена языка
![Image Alt](https://github.com/il427221-cyber/Crypto-Catalogue/blob/main/language%20change.jpg?raw=true)

### Фильтр монет
![Image Alt](https://github.com/il427221-cyber/Crypto-Catalogue/blob/main/coins%20filter.jpg?raw=true)

### Показ сохраненных данных
![Image Alt](https://github.com/il427221-cyber/Crypto-Catalogue/blob/main/show%20saved%20data.jpg?raw=true)

</details>
