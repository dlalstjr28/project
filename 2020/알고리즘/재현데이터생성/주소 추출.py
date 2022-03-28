import pandas as pd

listing = []
title = "도로명정보조회"
col= ["시군구","도로명번호","도로명","로마자표기","읍면동일련번호","시도명","시군구명","읍면동구분","읍면동코드","읍면동명"]

for i in range(0,17):
    findtitle = title+str(i)+".xls"  ## 정리한 폴더 안에 있는 파일을 자동으로 열고 데이터를 가져오기 위한 string 값을 설정
                                     ## 엑셀 파일 ( 도로명정보조회 , 삭제된 도로명주소 // 갱신된 도로명주소는 너무 많아 엑셀파일로 제공하지 않는다. 따라서 삭제된(적음) 을 사용)
    print(findtitle)
    df = pd.read_excel(findtitle)
    index = len(df.index)
    df = pd.DataFrame(df)
    df.columns = df.iloc[0]  ## pandas.iloc[] 를 이용해 숫자를 통해 데이터프레임 접근 
    df.drop(0, inplace=True)
    ##df = df.iloc[2:,0:10]   위에 처리를 하지 않을 경우 따로 프레임워크를 구성하는 방법
    ##df.columns = col
    ##df.index = [i for i in range(1,index-1)]
    ##print(df.head())
    str1 = df['시도명'].unique()  ## 중복된 값을 삭제하고 하나로 만들어준다.
    str2 = df['시군구'].unique()  ## 칼럼에 해당되는 값들 해당
    string =""
    for i in str2:
        if len(str2) != 1:  ## 세종자치특별시에서는 시군구가 없기 때문에 오류 발생 따라서 조건문 생성
            string = str1[0] + " " + i
            listing.append(string)  ## 최종 지역을 저장하기 위한 append 이용
    ##print(df)
print(listing)
    
