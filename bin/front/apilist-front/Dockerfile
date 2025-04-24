FROM node:22.14-alpine3.20 as builder

WORKDIR /app
COPY package*.json ./
RUN --mount=type=cache,id=npm-cache,target=/app/node_modules npm install
RUN npm install --save-dev vite
COPY . .
RUN npm run build

# Stage 2: Create the production image
FROM nginx:1.27.4-alpine
#RUN rm /etc/nginx/conf.d/default.conf
COPY default.conf /etc/nginx/conf.d/default.conf
COPY nginx.conf /etc/nginx/nginx.conf
COPY --from=builder /app/dist /usr/share/nginx/html

EXPOSE 80

CMD ["nginx", "-g", "daemon off;"]
