# LightningServer
A simple server that emulates Optifine's one, making it capable of rendering visual addons.

## Requirements
- Java Development Kit (JDK) in version 11
- MongoDB and Redis server
- At least a little bit of knowledge on the subject.


## Usage
1. You should put a new entry into 'users' collection to your database, like this:
```json
{
  "username": "luvily",
  "cape": "0001"
}
```

2. You should put a new entry into 'capes' collection to your database, like this:
```json
{
  "identifier": "0001",
  "texture": "Here you should pass your binary form of image. (In the future there will be a post endpoint for it)"
}
```

3. Last and **most important** thing is changing your hosts file to [this](./assets/hosts) or just add this line to end of this file.
```
# Here you should pass your server address
127.0.0.1 s.optifine.net
```

## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

## License
This project is released under [Apache License 2.0](./LICENSE.md).