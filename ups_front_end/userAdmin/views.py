from django.shortcuts import render, redirect
from userAdmin.forms import UserLoginForm, PackageSearchForm
import psycopg2
# Create your views here.
class Packages():
    packageid=""
    truckid=""
    userid=""
    itemNum=""
    status=""
    destx=""
    desty=""

    def __init__(self, arg1, arg2, arg3, arg4, arg5, arg6, arg7):
        self.packageid = arg1
        self.truckid = arg2
        self.userid = arg3
        self.itemNum = arg4
        self.status = arg5
        self.destx = arg6
        self.desty = arg7


def connect_to_database():
    conn = psycopg2.connect(
        host="localhost",
        database="yunjia",
        user="postgres",
        password="passw0rd")
    return conn


def login(request):
    form = UserLoginForm()
    if request.method == 'POST':
        form = UserLoginForm(request.POST)
        if form.is_valid():
            userid = form.cleaned_data.get('userid')
            password = form.cleaned_data.get('password')
            #todo: find in table
            conn = connect_to_database()
            cur = conn.cursor()
            query = "SELECT password FROM \"user\" WHERE \"userId\"= %s"
            print(str(userid))
            print(query)
            cur.execute(query,(str(userid)))
            res = cur.fetchall()
            print(res[0][0])
            password_database = res[0][0]
            if(password_database==password):
                print("success")
                return redirect('home/{0}/'.format(userid))
            else:
                print("false")
                return render(request, 'login.html', {'prompt':"Invalid userid or password!"})  

    return render(request, 'login.html', {'form':form})            

def logout(request):
    return redirect('login')

def queryPackage(request, userid):
    print("enter")
    print(userid)
    packageSuitable = list()
    if request.method == 'GET':
        #todo: find in table
        conn = connect_to_database()
        cur = conn.cursor()
        query = "SELECT * FROM packages WHERE packages.userid= %s "
        cur.execute(query,(str(userid)))
        res = cur.fetchall()
        for r in res:
            newPackage = Packages(r[0],r[1],r[2],r[3],r[4],r[5],r[6])
            packageSuitable.append(newPackage)
    return render(request, 'query.html',{'packageSuitable':packageSuitable})  