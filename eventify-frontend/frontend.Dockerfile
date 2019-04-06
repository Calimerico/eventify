FROM node
ADD . /
RUN npm install
CMD ["npm", "start"]

