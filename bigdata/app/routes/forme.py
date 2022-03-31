from fastapi import APIRouter, Depends, HTTPException, status
from jose import jwt, JWTError
from sqlalchemy.orm import Session
from starlette.requests import Request
from starlette.responses import Response
from app.database.schema import User, Mood, UserMood, Place, PlaceWish, Feed, FeedLike, FeedMood
from app.database.conn import db
from app.common.config import Config

router = APIRouter(prefix="/data/main")

SECRET_KEY = Config.JWT_SECRET
ALGORITHM = Config.JWT_ALGORITHM
PREFIX = "Bearer "

# Header로 Authorization 받음
@router.get("/forme")
async def forme(request: Request, searchRadius: int, lat: float, lng: float, session: Session = Depends(db.session)):
    # 사용자 찾기
    credentials_exception = HTTPException(
        status_code=status.HTTP_401_UNAUTHORIZED,
        detail="Could not validate credentials",
        headers={"WWW-Authenticate": "Bearer"},
    )
    token = request.headers.get('Authorization')
    # 그 Authorization으로 받은 내용을 JWT로 해석하여서 email을 추출해냄
    try:
        token = token.replace(PREFIX, "")
        user_email = jwt.decode(token, SECRET_KEY, [ALGORITHM])["sub"] # {'sub': 'amlwq@naver.com', 'iss': 'ssafy.com', 'exp': 1649907298, 'iat': 1648611298} 
    except JWTError:
        raise credentials_exception
    # 해당 email로 user를 찾음
    print("----------------찾은 유저 출력---------------")
    user = session.query(User).filter(User.email == user_email).all()
    print("사용자 : ", user)
    return user