# Estágio 1: Build (Compilar a aplicação Angular)
# CORREÇÃO: Atualizando a versão do Node.js para a 20, que é compatível com o Angular moderno.
FROM node:20-alpine as build

# Define o diretório de trabalho dentro do contêiner
WORKDIR /usr/src/app

# Copia os arquivos de definição de pacotes
COPY package*.json ./

# Instala as dependências do projeto
RUN npm install

# Copia todo o código-fonte do Angular
COPY . .

# Roda o comando de build do Angular para gerar os arquivos de produção
# A pasta de saída `dist/clinicar-frontend/browser` é o padrão para versões recentes do Angular.
RUN npm run build

# Estágio 2: Production (Servir os arquivos com Nginx)
# Usamos uma imagem leve do Nginx para servir o site
FROM nginx:alpine

# Copia os arquivos estáticos compilados do estágio anterior para a pasta padrão do Nginx
COPY --from=build /usr/src/app/dist/clinicar-frontend/browser /usr/share/nginx/html

# Expõe a porta 80, que é a porta padrão do Nginx
EXPOSE 80

# Comando para iniciar o Nginx quando o contêiner for executado
CMD ["nginx", "-g", "daemon off;"]

