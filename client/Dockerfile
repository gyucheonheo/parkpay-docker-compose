FROM node:alpine

RUN mkdir -p /opt/client 
RUN apk add --no-cache libc6-compat
ENV NODE_ENV production
ENV PORT 3000

VOLUME /opt/client

WORKDIR /opt/client

COPY package.json /opt/client 
COPY package-lock.json /opt/client

RUN npm install --no-optional

COPY . /opt/client 

RUN npm run build

EXPOSE 3000

CMD [ "npm", "run", "start" ]
