# PostPlaceholderAPI

The **PostPlaceholderAPI** is a powerful Minecraft plugin that enables server administrators to retrieve placeholders using GET requests. With this plugin, you can seamlessly integrate dynamic content into your server, enhancing the player experience. By sending a GET request to the server, you can access various placeholders that provide real-time information about players.

You can get plugin from [Spigot](https://www.spigotmc.org/resources/postplaceholderapi.111988/) or [ModRinth](https://modrinth.com/plugin/postplaceholderapi).

## Features

- Fetch placeholders through GET requests.
- Retrieve player-related data using the provided format: `http://backend.ip:port/<player-uuid>/<placeholder-without-%>`.
- Example request: `http://example.com:8000/d2f0a4b6-260e-480b-a33c-bbe5a5db2d53/player_name` (returns JSON containing "playgamesgo").
- Requires inclusion of a valid **Token** in the request header to authorize access. Failure to do so results in a **401 Unauthorized** response.
- Example responses:
  - `{"status":"401","message":"Unauthorized"}` - Without Authorization Header
  - `{"status":"404","message":"Invalid URI"}` - With the Wrong URL Scheme
  - `{"status": "200", "message":"playgamesgo"}` - Valid Placeholder (`%player_name%`)

## Compatibility

- The PostPlaceholderAPI plugin is compatible with **Minecraft version 1.19 and above**.
- If you require plugin functionality for Minecraft version 1.18 or lower, consider using the [RestPlaceholderAPI](https://www.spigotmc.org/resources/rest-placeholderapi.90266/) plugin.
- Note that the RestPlaceholderAPI plugin is recommended for older versions, as the PostPlaceholderAPI plugin focuses on offering functionality for newer versions of Minecraft.

Enhance your server's capabilities and provide dynamic content effortlessly with the PostPlaceholderAPI plugin. Retrieve placeholders, deliver personalized experiences, and keep your players engaged like never before.
