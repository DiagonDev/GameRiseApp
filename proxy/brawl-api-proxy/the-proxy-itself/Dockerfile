FROM python:3-slim

WORKDIR /usr/src/app

COPY requirements.txt ./
RUN pip install --upgrade pip
RUN pip install --no-cache-dir -r requirements.txt

COPY . .

EXPOSE 5555

CMD [ "python3", "./proxy.py" ]
CMD ["gunicorn", "-w", "1", "-b", "0.0.0.0:5555", "proxy:proxy"]

