FROM node:10

RUN mkdir -p /srv/app/parkpay-admin-client
WORKDIR /srv/app/parkpay-admin-client

COPY package*.json ./ 

RUN npm install

COPY . .

RUN npm run build

EXPOSE 3000

CMD [ "npm", "run", "start" ]
