import styled from 'styled-components';

export const HeaderContainer = styled.div`
  position : relative; 
  margin-top: 20px;
  // margin : 0px 20px;
  display: flex;
  justify-content : space-evenly;
  // align-items : center;
`;

export const NicknameContainer = styled.p`
  font-weight: bold;
  font-family: 'NotoSansKR';
  width: 100px;
  height: 10px;
  // margin-left: 10px;
  cursor : pointer;
`;

export const ContentText = styled.div`
  // margin-left: 10px;
  margin-right: 20px;
`;

export const CustomText = styled.p`
  font-family: 'NotoSansKR';
  text-align: justify;
  font-weight: bold;
  margin-bottom : 0px;
`;

export const BelowContainer = styled.div`
`;

export const HeartContainer = styled.div`
  position : absolute;
  // clear : left;
  // float : right;
  right : 5%;
  bottom : 0;
  // margin-left : %;
  // align-items : center;
`;
