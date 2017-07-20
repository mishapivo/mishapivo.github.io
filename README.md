## [@mishapivo - material guidelines](http://mishapivo.github.io)


**@mishapivo material guidelines** представляет собой набор руководящих принципов дизайна, которые будут использоваться в качестве ссылки по команде - и клиенты - в то время как сотрудничество на веб - проекта. Хотя это особенно полезно для больших проектов, это может быть удобно и для маленьких.

Общим подходом к руководству стиля является создание файла Photoshop и его общий доступ. Проблема заключается в отсутствии гибкости. Файл .psd может раздражать обновление и совместное использование. Клиенты в большинстве случаев даже не откроют вам файлы Photoshop, поэтому вам лучше сохранить копии .jpg.

Используя руководство по стилю CSS, с другой стороны, это более удобно! У вас в вашем распоряжении мощь CSS (и SASS!) Для быстрого обновления. Как только он в сети, совместное использование куска пирога. Сделав его отзывчивым, вам не нужен другой файл для каждого устройства: просто используйте медиа-запросы, и элементы будут адаптированы к различным размерам видовых экранов.

Поэтому мы решили создать этот шаблон, который можно использовать для создания собственного руководства по стилю на лету!

Иконки были созданы талантливым Петрасом Наргелой и могут быть бесплатно загружены на Freebiesbug.






![Screenshot](screenshot.png)

Это простой и минималистский шаблон для Jekyll, предназначенный для разработчиков, которые хотят писать сообщения в блогах, но не хотят заботиться о внешнем виде.

Особенности:

- Gulp
- Stylus (Jeet, Rupture, Kouto Swiss)
- Smoothscroll
- Live Search
- Offcanvas Menu
- SVG icons
- Very very small and fast!
- Shell Script to create posts
- Tags page
- Series page
- About Me page
- Feed RSS
- Sitemap.xml
- Color Customization
- Info Customization

## Цветовая палитра |  Color Palette.
[Material Palette](https://material.io) на сайте: https://material.io
Color Theme: Blue, Green, Orange, yellow.
В процессе дизайна и прототипирования максимально придерживаемся Material Guidelines. Использование Flat палитры не запрещается.

## Шрифты | Fonts
В работе используем следующие Web шрифты: 
- [Font Google Roboto](https://google.fonts)
- [Font Awesome Icon 4.7](https://fontawesome.io)
- [Font Ionic Icon](https://ionicons.io)

## Аватар | Avatar
![My Avatar](https://mishapivo.githab.io/img/avatar.gpg)

## Имя | Name
Aqua Farm - Интернет 
## Слоган | Миссия | Ценности | Вижин | Цели

## Элементы

## CSS файл - Styleguide

## Basic Setup

1. [Install Jekyll](http://jekyllrb.com)
2. Fork the [Will Jekyll Template](https://github.com/willianjusten/will-jekyll-template/fork)
3. Clone the repo you just forked.
4. Edit `_config.yml` to personalize your site.
5. Просмотрите примеры сообщений в `_posts`, чтобы увидеть примеры для назначения категорий и тегов и других данных YAML.
6. Прочтите приведенную ниже документацию для дальнейших указателей настройки и документации.
7. **Не забудьте скомпилировать ваши файлы с помощью Gulp.**

## Настройки сайта и пользователя.

Вы должны заполнить некоторую информацию на `_config.yml`, чтобы настроить свой сайт.

```
# Настройка конфигуратора для развёртывания сайта на GitHub Pages
# Конфигурационный файл для сайта: https//mishapivo.github.io
# Из шаблона:  Настройка для развёртывания на WebStorm | Ruby + Jekkyl | Node.js + Hexo
# Михаил Пивоваренко | Персональная страница, биография, опыт, навыки, образование и портфолио!.
# IDE: WebStorm | Temlate: Tanya
# General Site Setting | Основные настройки сайта:

# Site - Сайт
title: Mikhail Pivovarenko                # Настройки сайта | Заголовок
subtitle: Developer & Web Designer        # Настройки сайта | Подзаголовок
description: Freelance                    # Настройки сайта | Описание сайта
author: Mikhail Pivovarenko               # Настройки сайта | Автор
language: ru                              # Настройки сайта | Язык сайта
timezone: Europe/Kiev                     # Настройки сайта | Временная зона

# URL
## If your site is put in a subdirectory, set url as 'http://yoursite.com/child' and root as '/child/'
## Если ваш сайт помещен в подкаталог, установите в url: 'http://yoursite.com/subdir' и установите в root: '/subdir/'
url: http://mishapivo.github.io           # Настройки сайта | Полный URL адресс сайта
root: /                                   # Настройки сайта | Установка базовой папки/директории сайта
# permalink: :year/:month/:day/:title/
# permalink_defaults:

# Directory
# source_dir: source
# public_dir: public
# tag_dir: tags
# archive_dir: archives
# category_dir: categories
# code_dir: downloads/code
# i18n_dir: :lang
# skip_render:

# Writing - Написание поста
new_post_name: :title.md # File name of new posts
default_layout: post
titlecase: false # Transform title into titlecase
external_link: true # Open external links in new tab
filename_case: 0
render_drafts: false
post_asset_folder: false
relative_link: false
future: true
highlight:
  enable: true
  line_number: true
  auto_detect: false
  tab_replace:

# Category & Tag - Категории & Теги
default_category: uncategorized
category_map:
tag_map:

# Date / Time format - Формат даты и времени
## Hexo uses Moment.js to parse and display date | Hexo использует Moment.js для синтаксического анализа и отображения даты
## You can customize the date format as defined in | Вы можете настроить формат даты, определенный в
## http://momentjs.com/docs/#/displaying/format/
date_format: YYYY-MM-DD                                   # Формат даты
time_format: HH:mm:ss                                     # Формат фремени

# Pagination - Пагинация
## Set per_page to 0 to disable pagination. - Установика per_page на 0 - отключает пагинацию.
per_page: 15                                              # Количество страниц
pagination_dir: page                                      # Способ пагинации

# Extensions - Расширения
## Plugins: https://hexo.io/plugins/                      #
## Themes: https://hexo.io/themes/                        #
# theme: material

# Deployment - Развёртывание
## Docs: https://hexo.io/docs/deployment.html
deploy:
  type:
```

**Не забудьте изменить свой baseurl, прежде чем строить свой сайт!**

## Color customization

Все цветовые переменные находятся в `src / style / variable`. Чтобы изменить основной цвет, просто установите новое значение в `main`. Другие цвета предназначены для текстов и цвета фона кода.

## Создание поста

Вы можете использовать `initpost.sh` для создания новых сообщений. Просто выполните команду:

```
./initpost.sh -c Post Title
```

Новый файл будет создан в `_posts` с этим форматом` date-title.md`.

## Предисловие

Когда вы создаете новое сообщение, вам нужно заполнить информацию о постах в передней части, следуя этому примеру:

```
---
layout: post
title: "Как использовать"
date: 2015-08-03 03:32:44
image: '/assets/img/post-image.png'
description: 'Первые шаги по использованию этого шаблона'
tags:
- jekyll 
- template 
categories:
- I love Jekyll
twitter_text: 'How to install and use this template'
---
```

## Running the blog in local

Чтобы скомпилировать активы и запустить Jekyll на локальном компьютере, вам необходимо выполнить следующие шаги:

- Install [NodeJS](https://nodejs.org/)
- Run `npm install` 
- Run `gulp`

## Questions

У вас проблемы с работой или хотите знать, почему я что-то настраиваю определенным образом? Ping me on Twitter [@willian_justen](https://twitter.com/willian_justen) or file a [GitHub Issue](https://github.com/willianjusten/will-jekyll-template/issues/new).

## Donation

Если вам понравилась моя работа, купите мне кофе <3

[![paypal](https://www.paypalobjects.com/en_US/i/btn/btn_donateCC_LG.gif)](https://www.paypal.com/cgi-bin/webscr?cmd=_s-xclick&hosted_button_id=UTMFZUHX6EUGE)

## License

Эта тема - бесплатное программное обеспечение с открытым исходным кодом, распространяемое по лицензии MIT. Поэтому не стесняйтесь использовать эту тему Jekyll на своем сайте, не связывая её со мной и без какой либо ответственности с моей стороны.

If you’d like to give me credit somewhere on your blog or tweet a shout out to [@willian_justen](https://twitter.com/willian_justen), that would be pretty sweet.
