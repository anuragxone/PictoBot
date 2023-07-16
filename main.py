import asyncio
import sys

import BingImageCreator


def print_hi(name):
    print(f'Hi, {name}')


async def fetchImageLinks(string):
    linksFile = open("links.txt", "w")
    imageGenerator = BingImageCreator.ImageGenAsync(auth_cookie="1ISX-G7Xc48ZwVRNA7JCpkM93ZGl6CL6l-3RESsg7O2JbOrbpGSMvuvq6uFg3E_JiOwbgViKlQM088OybjMtiuifECe81UlI-NzIjH5vtFn4axIKu3rLRJ7GZXsxCVnCZ0Yh0chxNdrV5aonDlBX3hHAadxS_lqnWR9ku4Nfy42Lb6UNo_7DSKquwodBMA1VdbstiuFBwQi_PbL0EOl2Jmw")
    listOfLinks = await imageGenerator.get_images(string)
    for links in listOfLinks:
        linksFile.write(links+'\n')
    linksFile.close()
    return listOfLinks


if __name__ == '__main__':
    prompt = sys.argv[1]
    print(asyncio.run(fetchImageLinks(prompt)))
