import petname #pip install petname==2.2
import random
from faker import Faker #pip install Faker
import json


class Owner:
    def __init__(self, name, address, birthdate, telephone, id, pets):
        self.name = name
        self.address = address
        self.birthdate = birthdate
        self.telephone = telephone
        self.id = id
        self.pets = pets

    def encode(self):
        return self.__dict__


class Pet:
    def __init__(self, name, specie, description, gender, id, weight):
        self.name = name
        self.specie = specie
        self.description = description
        self.gender = gender
        self.id = id
        self.weight = weight

    def encode(self):
        return self.__dict__


# n -> numero de owners
# m -> numero de maximo de pets por pessoa
def generator(n, m):
    fake = Faker()
    owners = []
    for i in range(0, n):
        # PET
        pets = []
        for j in range(0, random.randint(1, m)):
            pets.append(Pet(petname.name(), petname.Generate(), petname.adjective(),
                            'female' if random.random() > 0.5 else 'male', random.randint(0, 100),
                            random.randint(0, 1000000)))

        # OWNER
        owners.append(Owner(fake.name(), fake.address(), fake.date(), fake.phone_number(),
                            random.randint(0, 1000000), pets))

    jsontracks = json.dumps(owners, default=lambda o: o.encode(), indent=4)
    with open('owners.json','w') as f:
        f.write(jsontracks)


generator(5, 10)
